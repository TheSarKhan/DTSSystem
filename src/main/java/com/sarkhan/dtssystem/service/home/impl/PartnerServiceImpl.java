package com.sarkhan.dtssystem.service.home.impl;


import com.sarkhan.dtssystem.dto.home.PartnerDTO;
import com.sarkhan.dtssystem.model.content.home.data.Partner;
import com.sarkhan.dtssystem.repository.content.home.PartnerRepository;
import com.sarkhan.dtssystem.service.FileStorageService;
import com.sarkhan.dtssystem.service.home.service.PartnerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PartnerServiceImpl implements PartnerService {
    private final PartnerRepository partnerRepository;
    private final FileStorageService fileStorageService;

    @Override
    public List<Partner> getAll() {
        return partnerRepository.findAll();
    }

    @Override
    public Optional<Partner> getById(Long id) {
        return partnerRepository.findById(id);
    }

    @Override
    public Partner create(PartnerDTO partnerDTO, MultipartFile partnerImage) throws IOException {
        Partner partner = new Partner();
        partner.setCompanyName(partnerDTO.getCompanyName());
        partner.setCompanyWebsite(partnerDTO.getCompanyWebsite());
        partner.setCompanyPhoto(fileStorageService.saveFile(partnerImage));
        return partnerRepository.save(partner);
    }

    @Override
    public Partner update(Long id, PartnerDTO partnerDTO, MultipartFile partnerImage) throws IOException {
        if (!partnerRepository.existsById(id)) {
            throw new RuntimeException("Partner not found");
        }
        Partner partner = partnerRepository.findById(id).orElseThrow(() -> new RuntimeException("Partnyor tapılmadı!"));
        fileStorageService.deleteFile(partner.getCompanyPhoto());
        partner.setCompanyWebsite(partnerDTO.getCompanyWebsite());
        partner.setCompanyName(partnerDTO.getCompanyName());
        partner.setCompanyPhoto(fileStorageService.saveFile(partnerImage));
        return partnerRepository.save(partner);
    }

    @Override
    public void delete(Long id) {
        partnerRepository.deleteById(id);
    }
}