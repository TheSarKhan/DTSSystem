package com.sarkhan.dtssystem.service.eligibility.impl;

import com.sarkhan.dtssystem.model.content.eligibility.Eligibility;
import com.sarkhan.dtssystem.model.content.eligibility.data.Criteria;
import com.sarkhan.dtssystem.model.content.eligibility.data.Metadology;
import com.sarkhan.dtssystem.repository.content.eligibility.CriteriaRepository;
import com.sarkhan.dtssystem.repository.content.eligibility.MetadologyRepository;
import com.sarkhan.dtssystem.service.eligibility.service.EligibilityService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EligibilityServiceImpl implements EligibilityService {
    @Value("${base-url}")
    private String baseUrl;
    private final CriteriaRepository criteriaRepository;
    private final MetadologyRepository metadologyRepository;

    @Override
    public Eligibility getEligibilityData() {
        Eligibility eligibility = new Eligibility();
        List<Criteria> criterias = criteriaRepository.findAll();
        List<Metadology> metadologies = metadologyRepository.findAll();
        for (Criteria x : criterias) {
            x.setIconUrl(baseUrl + "/uploads/" + x.getIconUrl());
        }
        for (Metadology x : metadologies) {
            x.setIconUrl(baseUrl + "/uploads/" + x.getIconUrl());
        }
        eligibility.setCriterias(criterias);
        eligibility.setMetadologies(metadologies);

        return eligibility;
    }
}
