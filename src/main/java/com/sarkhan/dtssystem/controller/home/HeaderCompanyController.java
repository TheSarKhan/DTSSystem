package com.sarkhan.dtssystem.controller.home;

import com.sarkhan.dtssystem.dto.home.HeaderCompanyDTO;
import com.sarkhan.dtssystem.model.content.home.data.HeaderCompany;
import com.sarkhan.dtssystem.service.home.service.HeaderCompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/header-companies")
@RequiredArgsConstructor
public class HeaderCompanyController {
    private final HeaderCompanyService headerCompanyService;

    @GetMapping
    public List<HeaderCompany> getAll() {
        return headerCompanyService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<HeaderCompany> getById(@PathVariable Long id) {
        return headerCompanyService.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public HeaderCompany create(@RequestPart HeaderCompanyDTO headerCompanyDTO, MultipartFile companyImage) throws IOException {
        return headerCompanyService.create(headerCompanyDTO,companyImage);
    }

    @PutMapping("/{id}")
    public HeaderCompany update(@PathVariable Long id, @RequestPart HeaderCompanyDTO headerCompanyDTO, MultipartFile companyImage) throws IOException {
        return headerCompanyService.update(id, headerCompanyDTO,companyImage);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        headerCompanyService.delete(id);
    }
}
