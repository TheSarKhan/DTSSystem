package com.sarkhan.dtssystem.controller;

import com.sarkhan.dtssystem.dto.request.CompanyRequest;
import com.sarkhan.dtssystem.service.CompanyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/v1/company")
@RequiredArgsConstructor
public class CompanyController {

     private final CompanyService companyService;

    @PostMapping("/add")
    public ResponseEntity<?> addCompany(
            @Valid @RequestPart("companyRequest") CompanyRequest companyRequest,
            @RequestPart("registerCertificate") MultipartFile registerCertificate,
            @RequestPart("financialStatement") MultipartFile financialStatement,
            @RequestPart("propertyLawCertificate") MultipartFile propertyLawCertificate
    ) throws IOException {

        if (companyRequest.getCompanyData().getCreateYear() > LocalDate.now().getYear()) {
            return ResponseEntity.status(400).body("Yaranma ili cari ildən böyük ola bilməz");
        }

        List<String> errors = new ArrayList<>();

         if (!isAllowed(registerCertificate, Set.of("pdf", "doc", "docx"))) {
            errors.add("Şirkətin dövlət reyestrindən çıxarışı sənədi pdf, doc və ya docx olmalıdır.");
        }

        if (!isAllowed(financialStatement, Set.of("pdf", "doc", "docx", "xls", "xlsx"))) {
            errors.add("Maliyyə hesabatları sənədi pdf, doc, docx, xls və ya xlsx olmalıdır.");
        }

        if (!isAllowed(propertyLawCertificate, Set.of("pdf", "doc", "docx"))) {
            errors.add("Təsdiqedici sənəd pdf, doc və ya docx olmalıdır.");
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