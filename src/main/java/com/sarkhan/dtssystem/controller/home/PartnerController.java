package com.sarkhan.dtssystem.controller.home;

import com.sarkhan.dtssystem.dto.home.PartnerDTO;
import com.sarkhan.dtssystem.model.content.home.data.Partner;
import com.sarkhan.dtssystem.service.home.service.PartnerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/partners")
@RequiredArgsConstructor
public class PartnerController {
    private final PartnerService partnerService;

    @GetMapping
    public List<Partner> getAll() {
        return partnerService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Partner> getById(@PathVariable Long id) {
        return partnerService.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Partner create(@RequestPart PartnerDTO partnerDTO,MultipartFile partnerImage) throws IOException {
        return partnerService.create(partnerDTO,partnerImage);
    }

    @PutMapping("/{id}")
    public Partner update(@PathVariable Long id, @RequestPart PartnerDTO partnerDTO, MultipartFile partnerImage) throws IOException {
        return partnerService.update(id, partnerDTO,partnerImage);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        partnerService.delete(id);
    }
}