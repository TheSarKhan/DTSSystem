package com.sarkhan.dtssystem.service.home.service;

import com.sarkhan.dtssystem.dto.home.PartnerDTO;
import com.sarkhan.dtssystem.model.content.home.data.Partner;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface PartnerService {
    List<Partner> getAll();
    Optional<Partner> getById(Long id);
    Partner create(PartnerDTO partnerDTO, MultipartFile partnerImage) throws IOException;
    Partner update(Long id, PartnerDTO partnerDTO,MultipartFile partnerImage) throws IOException;
    void delete(Long id);
}