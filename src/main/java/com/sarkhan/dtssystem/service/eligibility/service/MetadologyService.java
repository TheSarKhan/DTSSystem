package com.sarkhan.dtssystem.service.eligibility.service;

import com.sarkhan.dtssystem.dto.eligibility.MetadologyDTO;
import com.sarkhan.dtssystem.model.content.eligibility.data.Metadology;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface MetadologyService {
    Metadology create(MetadologyDTO dto, MultipartFile icon) throws IOException;

    Metadology getById(Long id);

    List<Metadology> getAll();

    Metadology update(Long id, MetadologyDTO dto, MultipartFile icon) throws IOException;

    void delete(Long id);
}
