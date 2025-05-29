package com.sarkhan.dtssystem.service.eligibility.impl;

import com.sarkhan.dtssystem.dto.eligibility.CriteriaDTO;
import com.sarkhan.dtssystem.model.content.eligibility.data.Criteria;
import com.sarkhan.dtssystem.repository.content.eligibility.CriteriaRepository;
import com.sarkhan.dtssystem.service.FileStorageService;
import com.sarkhan.dtssystem.service.eligibility.service.CriteriaService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CriteriaServiceImpl implements CriteriaService {

    private final CriteriaRepository criteriaRepository;
    private final FileStorageService fileStorageService;

    @Override
    public Criteria create(CriteriaDTO dto, MultipartFile icon) throws IOException {
        Criteria criteria = new Criteria();
        criteria.setDescription(dto.getDescription());
        criteria.setHeader(dto.getHeader());
        criteria.setIconUrl(fileStorageService.saveFile(icon));

        return criteriaRepository.save(criteria);
    }

    @Override
    public Criteria getById(Long id) {

        return criteriaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Criteria not found"));
    }

    @Override
    public List<Criteria> getAll() {
        return criteriaRepository.findAll();
    }

    @Override
    public Criteria update(Long id, CriteriaDTO dto, MultipartFile icon) throws IOException {
        Criteria criteria = criteriaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Criteria not found"));
        fileStorageService.deleteFile(criteria.getIconUrl());
        criteria.setDescription(dto.getDescription());
        criteria.setHeader(dto.getHeader());
        criteria.setIconUrl(fileStorageService.saveFile(icon));

        return criteriaRepository.save(criteria);
    }

    @Override
    public void delete(Long id) {
        criteriaRepository.deleteById(id);
    }
}
