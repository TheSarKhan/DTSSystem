package com.sarkhan.dtssystem.model.company.data;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CompanyFiles {
    String registerCertificate;
    String financialStatement;
    String digitalTransformationPlans;
}
