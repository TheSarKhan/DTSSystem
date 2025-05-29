package com.sarkhan.dtssystem.service.home.impl;


import com.sarkhan.dtssystem.dto.home.HeaderCompanyDTO;
import com.sarkhan.dtssystem.model.content.home.data.HeaderCompany;
import com.sarkhan.dtssystem.repository.content.home.HeaderCompanyRepository;
import com.sarkhan.dtssystem.service.FileStorageService;
import com.sarkhan.dtssystem.service.home.service.HeaderCompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class HeaderCompanyServiceImpl implements HeaderCompanyService {
    private final HeaderCompanyRepository headerCompanyRepository;
private final FileStorageService fileStorageService;
    @Override
    public List<HeaderCompany> getAll() {
        return headerCompanyRepository.findAll();
    }

    @Override
    public Optional<HeaderCompany> getById(Long id) {
        return headerCompanyRepository.findById(id);
    }

    @Override
    public HeaderCompany create(HeaderCompanyDTO headerCompanyDTO, MultipartFile companyImage) throws IOException {
        HeaderCompany headerCompany=new HeaderCompany();
        headerCompany.setCompanyName(headerCompanyDTO.getCompanyName());
        headerCompany.setPhotoUrl(fileStorageService.saveFile(companyImage));
        return headerCompanyRepository.save(headerCompany);
    }

    @Override
    public HeaderCompany update(Long id, HeaderCompanyDTO headerCompanyDTO, MultipartFile companyImage) throws IOException {
        if (!headerCompanyRepository.existsById(id)) {
            throw new RuntimeException("HeaderCompany not found");
        }
        HeaderCompany headerCompany=headerCompanyRepository.findById(id).orElseThrow(() -> new RuntimeException("Şirkət tapılmadı!"));
        fileStorageService.deleteFile(headerCompany.getPhotoUrl());
        headerCompany.setCompanyName(headerCompanyDTO.getCompanyName());
        headerCompany.setPhotoUrl(fileStorageService.saveFile(companyImage));
        return headerCompanyRepository.save(headerCompany);
    }

    @Override
    public void delete(Long id) {
        headerCompanyRepository.deleteById(id);
    }
}