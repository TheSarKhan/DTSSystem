package com.sarkhan.dtssystem.service.eligibility.service;

import com.sarkhan.dtssystem.dto.eligibility.CriteriaDTO;
import com.sarkhan.dtssystem.model.content.eligibility.data.Criteria;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface CriteriaService {
    Criteria create(CriteriaDTO dto, MultipartFile icon) throws IOException;
    Criteria getById(Long id);
    List<Criteria> getAll();
    Criteria update(Long id, CriteriaDTO dto, MultipartFile icon) throws IOException;
    void delete(Long id);
}
