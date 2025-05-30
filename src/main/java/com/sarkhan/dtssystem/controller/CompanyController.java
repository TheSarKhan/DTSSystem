package com.sarkhan.dtssystem.controller;

import com.sarkhan.dtssystem.config.PdfScriptChecker;
import com.sarkhan.dtssystem.dto.request.CompanyRequest;
import com.sarkhan.dtssystem.service.CompanyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("/api/v1/company")
@RequiredArgsConstructor
public class CompanyController {

    private final CompanyService companyService;
    private final PdfScriptChecker pdfScriptChecker;
    @Value("${secret-key}")
    private String secretToken;


    public boolean verifyRecaptcha(String recaptchaToken) {
        String verifyUrl = "https://www.google.com/recaptcha/api/siteverify";

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("secret", secretToken);  // Secret token env'den geliyor olmalı
        params.add("response", recaptchaToken);

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(params, headers);

        ResponseEntity<Map> response = restTemplate.postForEntity(verifyUrl, request, Map.class);
        Map<String, Object> body = response.getBody();

        if (body == null) return false;

        System.out.println("reCAPTCHA token: " + recaptchaToken);
        System.out.println("reCAPTCHA yanıtı: " + body);

        Object successObj = body.get("success");
        return successObj instanceof Boolean && (Boolean) successObj;
    }

    @PostMapping("/add")
    public ResponseEntity<?> addCompany(
            @Valid @RequestPart("companyRequest") CompanyRequest companyRequest,
            @RequestPart("registerCertificate") MultipartFile registerCertificate,
            @RequestPart("financialStatement") MultipartFile financialStatement,
            @RequestPart("propertyLawCertificate") MultipartFile propertyLawCertificate,
            @RequestParam("recaptchaToken") String recaptchaToken
    ) throws IOException {
        if (!verifyRecaptcha(recaptchaToken)) {
            return ResponseEntity.status(400).body("reCAPTCHA yoxlaması uğursuz oldu");
        }
        if (companyRequest.getCompanyData().getCreateYear() > LocalDate.now().getYear()) {
            return ResponseEntity.status(400).body("Yaranma ili cari ildən böyük ola bilməz");
        }

        List<String> errors = new ArrayList<>();

        if (registerCertificate.getOriginalFilename().endsWith(".pdf")) {
            List<String> jsCodes = pdfScriptChecker.findJavaScriptCodes(registerCertificate.getInputStream());
            if (!jsCodes.isEmpty()) {
                errors.add("Şirkətin dövlət reyestrindən çıxarışı sənədində təhlükəli skript aşkarlandı!");
            }
        }

        if (!isAllowed(financialStatement, Set.of("pdf", "doc", "docx", "xls", "xlsx"))) {
            errors.add("Maliyyə hesabatları sənədi pdf, doc, docx, xls və ya xlsx olmalıdır.");
        } else if (financialStatement.getOriginalFilename().endsWith(".pdf")) {
            List<String> jsCodes = pdfScriptChecker.findJavaScriptCodes(financialStatement.getInputStream());
            if (!jsCodes.isEmpty()) {
                errors.add("Maliyyə hesabatları sənədində təhlükəli skript aşkarlandı!");
            }
        }

        if (!isAllowed(propertyLawCertificate, Set.of("pdf", "doc", "docx"))) {
            errors.add("Təsdiqedici sənəd pdf, doc və ya docx olmalıdır.");
        } else if (propertyLawCertificate.getOriginalFilename().endsWith(".pdf")) {
            List<String> jsCodes = pdfScriptChecker.findJavaScriptCodes(propertyLawCertificate.getInputStream());
            if (!jsCodes.isEmpty()) {
                errors.add("Təsdiqedici sənəddə təhlükəli skript aşkarlandı!");
            }
        }

        if (!errors.isEmpty()) {
            return ResponseEntity.status(400).body(errors);
        }

        companyService.addCompany(companyRequest, financialStatement, registerCertificate, propertyLawCertificate);
        return ResponseEntity.status(201).body("Müraciətiniz uğurla gerçəkləşdi");
    }


    private boolean isAllowed(MultipartFile file, Set<String> allowedExtensions) {
        if (file.isEmpty()) return false;

        String fileName = file.getOriginalFilename();
        if (fileName == null || !fileName.contains(".")) return false;

        String extension = fileName.substring(fileName.lastIndexOf('.') + 1).toLowerCase();
        return allowedExtensions.contains(extension);
    }


    @GetMapping("/export-excel")
    public ResponseEntity<byte[]> exportCompaniesToExcel() throws IOException {
        byte[] excelData = companyService.exportCompaniesToExcel();

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=companies.xlsx");

        return new ResponseEntity<>(excelData, headers, HttpStatus.OK);
    }
}