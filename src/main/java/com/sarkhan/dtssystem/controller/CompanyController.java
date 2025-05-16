package com.sarkhan.dtssystem.controller;

import com.sarkhan.dtssystem.dto.request.CompanyRequest;
import com.sarkhan.dtssystem.repository.company.CompanyRepository;
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

@RestController
@RequestMapping("/api/v1/company")
@RequiredArgsConstructor
public class CompanyController {

    private final CompanyRepository companyRepository;
    private final CompanyService companyService;

    @PostMapping("/add")
    public ResponseEntity<?> addCompany(@Valid @RequestPart("companyRequest") CompanyRequest companyRequest, MultipartFile registerCertificate, MultipartFile financialStatement ,MultipartFile propertyLawCertificate) throws IOException {
        if (companyRequest.getCompanyData().getCreateYear() > LocalDate.now().getYear()) {
            return ResponseEntity.status(400).body("Yaranma ili cari ildən böyük ola bilməz");
         }
        companyService.addCompany(companyRequest, financialStatement, registerCertificate,propertyLawCertificate);
        return ResponseEntity.status(201).body("Müraciətiniz uğurla gerçəkləşdi");
    }

    @GetMapping("/export-excel")
    public ResponseEntity<byte[]> exportCompaniesToExcel() throws IOException {
        byte[] excelData = companyService.exportCompaniesToExcel();

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=companies.xlsx");

        return new ResponseEntity<>(excelData, headers, HttpStatus.OK);
    }
}