package com.sarkhan.dtssystem.dto.request;

import com.sarkhan.dtssystem.model.company.data.*;
import jakarta.validation.constraints.*; // ya da javax.validation.constraints.*; projenin sürümüne göre
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CompanyRequest {
    CompanyData companyData;
    CompanySize companySize;
    DeclarationConsent declarationConsent;
    DigitalLeadership digitalLeadership;
    DigitalReadiness digitalReadiness;
    FinancialNeeding financialNeeding;
    IndustrialBusinessOperations industrialBusinessOperations;
    OwnershipLegal ownershipLegal;
}
