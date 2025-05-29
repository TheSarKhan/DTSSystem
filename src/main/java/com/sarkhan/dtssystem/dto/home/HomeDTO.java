package com.sarkhan.dtssystem.dto.home;


import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)

public class HomeDTO {
    private List<CardDTO> cards;
    private List<HeaderCompanyDTO> headerCompanies;
    private List<PartnerDTO> partners;
    private List<ProgramScopeDTO> programScopes;
}
