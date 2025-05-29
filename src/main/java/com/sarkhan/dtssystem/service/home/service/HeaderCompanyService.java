package com.sarkhan.dtssystem.service.home.service;

import com.sarkhan.dtssystem.dto.home.HeaderCompanyDTO;
import com.sarkhan.dtssystem.model.content.home.data.HeaderCompany;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface HeaderCompanyService {
    List<HeaderCompany> getAll();

    Optional<HeaderCompany> getById(Long id);

    HeaderCompany create(HeaderCompanyDTO headerCompanyDTO, MultipartFile companyImage) throws IOException;

    HeaderCompany update(Long id, HeaderCompanyDTO headerCompanyDTO, MultipartFile companyImage) throws IOException;

    void delete(Long id);
}