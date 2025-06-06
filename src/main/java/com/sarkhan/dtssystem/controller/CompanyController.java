package com.sarkhan.dtssystem.controller;

import com.sarkhan.dtssystem.config.PdfScriptChecker;
import com.sarkhan.dtssystem.dto.request.CompanyRequest;
import com.sarkhan.dtssystem.model.company.Company;
import com.sarkhan.dtssystem.repository.company.CompanyRepository;
import com.sarkhan.dtssystem.service.CompanyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class CompanyController {

    private final CompanyService companyService;
    private final PdfScriptChecker pdfScriptChecker;
    private final CompanyRepository companyRepository;

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

        log.info("Yeni şirkət müraciəti alındı: {} ({})  ",
                companyRequest.getCompanyData().getCompanyName(),
                companyRequest.getCompanyData().getCompanyRegisterNumber());

        if (!verifyRecaptcha(recaptchaToken)) {
            log.warn("reCAPTCHA doğrulaması uğursuz oldu.  ");

            return ResponseEntity.status(400).body("reCAPTCHA yoxlaması uğursuz oldu");
        }

        if (companyRequest.getCompanyData().getCreateYear() > LocalDate.now().getYear()) {
            log.warn("Yaranma ili cari ildən böyük: {}  ", companyRequest.getCompanyData().getCreateYear());
            return ResponseEntity.status(400).body("Yaranma ili cari ildən böyük ola bilməz");
        }

        List<String> errors = new ArrayList<>();

        // 1. Dövlət reyestri çıxarışı
        if (registerCertificate.getOriginalFilename().endsWith(".pdf")) {
            List<String> jsCodes = pdfScriptChecker.findJavaScriptCodes(registerCertificate.getInputStream());
            if (!jsCodes.isEmpty()) {
                log.error("Reyestr sənədində JavaScript tapıldı: {} ", jsCodes);
                errors.add("Şirkətin dövlət reyestrindən çıxarışı sənədində təhlükəli skript aşkarlandı!");
            }
        }

        // 2. Maliyyə hesabatı
        if (!isAllowed(financialStatement, Set.of("pdf", "doc", "docx", "xls", "xlsx"))) {
            log.warn("Maliyyə sənədinin formatı düzgün deyil: {} ", financialStatement.getOriginalFilename());
            errors.add("Maliyyə hesabatları sənədi pdf, doc, docx, xls və ya xlsx olmalıdır.");
        } else if (financialStatement.getOriginalFilename().endsWith(".pdf")) {
            List<String> jsCodes = pdfScriptChecker.findJavaScriptCodes(financialStatement.getInputStream());
            if (!jsCodes.isEmpty()) {
                log.error("Maliyyə sənədində JavaScript tapıldı: {} ", jsCodes);
                errors.add("Maliyyə hesabatları sənədində təhlükəli skript aşkarlandı!");
            }
        }

        // 3. Əmlak sənədi
        if (!isAllowed(propertyLawCertificate, Set.of("pdf", "doc", "docx"))) {
            log.warn("Əmlak sənədinin formatı düzgün deyil: {} ", propertyLawCertificate.getOriginalFilename());
            errors.add("Təsdiqedici sənəd pdf, doc və ya docx olmalıdır.");
        } else if (propertyLawCertificate.getOriginalFilename().endsWith(".pdf")) {
            List<String> jsCodes = pdfScriptChecker.findJavaScriptCodes(propertyLawCertificate.getInputStream());
            if (!jsCodes.isEmpty()) {
                log.error("Əmlak sənədində JavaScript tapıldı: {} ", jsCodes);
                errors.add("Təsdiqedici sənəddə təhlükəli skript aşkarlandı!");
            }
        }

        if (!errors.isEmpty()) {
            log.warn("Şirkət əlavə edilə bilmədi. Xətalar: {} ", errors);
            return ResponseEntity.status(400).body(errors);
        }

        companyService.addCompany(companyRequest, financialStatement, registerCertificate, propertyLawCertificate);
        log.info("Şirkət uğurla əlavə edildi: {} ", companyRequest.getCompanyData().getCompanyRegisterNumber());

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
        log.info("Şirkət siyahısı Excel formatında istənildi ");

        byte[] excelData = companyService.exportCompaniesToExcel();

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=companies.xlsx");

        log.info("Excel faylı hazırlandı, {} byte ", excelData.length);
        return new ResponseEntity<>(excelData, headers, HttpStatus.OK);
    }

    @GetMapping("/getAll")
    public List<Company> getAllCompaniesAsJSON(@RequestHeader("Authorization") String token) {
        log.info("Şirkət siyahısı JSON formatında istənildi ");
        List<Company> companies = companyRepository.findAll();
        return companies;
    }
}