package com.sarkhan.dtssystem.mapper;

import com.sarkhan.dtssystem.dto.home.CardDTO;
import com.sarkhan.dtssystem.dto.home.HeaderCompanyDTO;
import com.sarkhan.dtssystem.dto.home.PartnerDTO;
import com.sarkhan.dtssystem.dto.home.ProgramScopeDTO;
import com.sarkhan.dtssystem.model.content.home.data.Card;
import com.sarkhan.dtssystem.model.content.home.data.HeaderCompany;
import com.sarkhan.dtssystem.model.content.home.data.Partner;
import com.sarkhan.dtssystem.model.content.home.data.ProgramScope;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface HomeMapper {
    CardDTO toDto(Card card);
    HeaderCompanyDTO toDto(HeaderCompany headerCompany);
    PartnerDTO toDto(Partner partner);
    ProgramScopeDTO toDto(ProgramScope programScope);
}

