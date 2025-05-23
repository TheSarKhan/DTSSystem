package com.sarkhan.dtssystem.service;

import com.sarkhan.dtssystem.dto.request.CompanyRequest;
import com.sarkhan.dtssystem.model.company.Company;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface CompanyService {
    Company addCompany(CompanyRequest companyRequest, MultipartFile financialStatement, MultipartFile registerCertificate,MultipartFile propertyLawCertificate) throws IOException;
    byte[] exportCompaniesToExcel() throws IOException;

}
