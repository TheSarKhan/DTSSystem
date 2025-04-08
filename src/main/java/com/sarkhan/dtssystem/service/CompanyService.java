package com.sarkhan.dtssystem.service;

import com.sarkhan.dtssystem.dto.request.CompanyRequest;
import com.sarkhan.dtssystem.model.company.Company;

import java.io.IOException;

public interface CompanyService {
    Company addCompany(CompanyRequest companyRequest);
    byte[] exportCompaniesToExcel() throws IOException;

}
