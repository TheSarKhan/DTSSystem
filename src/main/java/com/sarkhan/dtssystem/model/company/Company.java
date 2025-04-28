package com.sarkhan.dtssystem.model.company;

import com.sarkhan.dtssystem.model.company.data.*;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    Long id;
@JdbcTypeCode(SqlTypes.JSON)
CompanyData companyData;
    @JdbcTypeCode(SqlTypes.JSON)
    CompanyFiles companyFiles;
    @JdbcTypeCode(SqlTypes.JSON)
    CompanySize companySize;
    @JdbcTypeCode(SqlTypes.JSON)
    DeclarationConsent declarationConsent;
    @JdbcTypeCode(SqlTypes.JSON)
    DigitalLeadership digitalLeadership;
    @JdbcTypeCode(SqlTypes.JSON)
DigitalReadiness digitalReadiness;
    @JdbcTypeCode(SqlTypes.JSON)
    FinancialNeeding financialNeeding;
    @JdbcTypeCode(SqlTypes.JSON)
IndustrialBusinessOperations industrialBusinessOperations;
    @JdbcTypeCode(SqlTypes.JSON)
OwnershipLegal ownershipLegal;

}
