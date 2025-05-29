package com.sarkhan.dtssystem.service.faq.service;

import com.sarkhan.dtssystem.dto.eligibility.CriteriaDTO;
import com.sarkhan.dtssystem.dto.faq.FaqDTO;
import com.sarkhan.dtssystem.model.content.eligibility.data.Criteria;
import com.sarkhan.dtssystem.model.content.faq.FAQ;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface FaqService {
    FAQ create(FaqDTO dto);


    FAQ getById(Long id);

    List<FAQ> getAll();


    FAQ update(Long id, FaqDTO dto);

    void delete(Long id);
}
