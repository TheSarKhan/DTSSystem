package com.sarkhan.dtssystem.mapper;

import com.sarkhan.dtssystem.dto.request.CompanyRequest;
import com.sarkhan.dtssystem.model.company.Company;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CompanyMapper {
    Company toEntity(CompanyRequest request);
    CompanyRequest toDto(Company company);
}

