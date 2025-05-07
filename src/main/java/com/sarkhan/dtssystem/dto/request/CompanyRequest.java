package com.sarkhan.dtssystem.dto.request;

import com.sarkhan.dtssystem.model.company.data.*;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CompanyRequest {
    @Valid
    CompanyData companyData;
    @Valid
    DeclarationConsent declarationConsent;
    @Valid
    DigitalLeadership digitalLeadership;
    @Valid
    DigitalReadiness digitalReadiness;
    @Valid
    FinancialNeeding financialNeeding;
@Valid
    PropertyLaw propertyLaw;
}
