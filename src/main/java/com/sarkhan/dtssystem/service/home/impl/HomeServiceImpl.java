package com.sarkhan.dtssystem.service.home.impl;

import com.sarkhan.dtssystem.dto.home.*;
import com.sarkhan.dtssystem.mapper.HomeMapper;
import com.sarkhan.dtssystem.repository.content.home.CardRepository;
import com.sarkhan.dtssystem.repository.content.home.HeaderCompanyRepository;
import com.sarkhan.dtssystem.repository.content.home.PartnerRepository;
import com.sarkhan.dtssystem.repository.content.home.ProgramScopeRepository;
import com.sarkhan.dtssystem.service.home.service.HomeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HomeServiceImpl implements HomeService {
    private final CardRepository cardRepository;
    private final PartnerRepository partnerRepository;
    private final HeaderCompanyRepository headerCompanyRepository;
    private final ProgramScopeRepository programScopeRepository;
    private final HomeMapper homeMapper;

    @Override
    public HomeDTO getHomeData() {
        List<CardDTO> cards = cardRepository.findAll().stream()
                .map(homeMapper::toDto)
                .toList();

        List<HeaderCompanyDTO> headerCompanies = headerCompanyRepository.findAll().stream()
                .map(homeMapper::toDto)
                .toList();

        List<PartnerDTO> partners = partnerRepository.findAll().stream()
                .map(homeMapper::toDto)
                .toList();

        List<ProgramScopeDTO> programScopes = programScopeRepository.findAll().stream()
                .map(homeMapper::toDto)
                .toList();

        return new HomeDTO(cards, headerCompanies, partners, programScopes);
    }
}
