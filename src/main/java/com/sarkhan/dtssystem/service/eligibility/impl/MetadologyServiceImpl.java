package com.sarkhan.dtssystem.service.eligibility.impl;

import com.sarkhan.dtssystem.dto.eligibility.MetadologyDTO;
import com.sarkhan.dtssystem.model.content.eligibility.data.Metadology;
import com.sarkhan.dtssystem.repository.content.eligibility.MetadologyRepository;
import com.sarkhan.dtssystem.service.FileStorageService;
import com.sarkhan.dtssystem.service.eligibility.service.MetadologyService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MetadologyServiceImpl implements MetadologyService {

    private final MetadologyRepository metadologyRepository;
    private final FileStorageService fileStorageService;

    @Override
    public Metadology create(MetadologyDTO dto, MultipartFile icon) throws IOException {
        Metadology metadology = new Metadology();
        metadology.setDescription(dto.getDescription());
        metadology.setHeader(dto.getHeader());
        metadology.setIconUrl(fileStorageService.saveFile(icon));

        return metadologyRepository.save(metadology);
    }

    @Override
    public Metadology getById(Long id) {

        return metadologyRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Metadology not found"));
    }

    @Override
    public List<Metadology> getAll() {
        return metadologyRepository.findAll();
    }

    @Override
    public Metadology update(Long id, MetadologyDTO dto, MultipartFile icon) throws IOException {
        Metadology metadology = metadologyRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Metadology not found"));
        fileStorageService.deleteFile(metadology.getIconUrl());
     metadology.setHeader(dto.getHeader());
     metadology.setDescription(dto.getDescription());
     metadology.setIconUrl(fileStorageService.saveFile(icon));

        return metadologyRepository.save(metadology);
    }

    @Override
    public void delete(Long id) {
        metadologyRepository.deleteById(id);
    }
}
