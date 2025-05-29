package com.sarkhan.dtssystem.controller.eligibility;

import com.sarkhan.dtssystem.dto.eligibility.MetadologyDTO;
import com.sarkhan.dtssystem.model.content.eligibility.data.Metadology;
import com.sarkhan.dtssystem.service.eligibility.service.MetadologyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/metadology")
@RequiredArgsConstructor
public class MetadologyController {

    private final MetadologyService metadologyService;

    @PostMapping
    public ResponseEntity<Metadology> create(@RequestPart MetadologyDTO metadologyDTO, MultipartFile icon) throws IOException {
        return ResponseEntity.ok(metadologyService.create(metadologyDTO,icon));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Metadology> getById(@PathVariable Long id) {
        return ResponseEntity.ok(metadologyService.getById(id));
    }

    @GetMapping
    public ResponseEntity<List<Metadology>> getAll() {
        return ResponseEntity.ok(metadologyService.getAll());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Metadology> update(@PathVariable Long id, @RequestPart MetadologyDTO metadologyDTO, MultipartFile icon) throws IOException {
        return ResponseEntity.ok(metadologyService.update(id, metadologyDTO,icon));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        metadologyService.delete(id);
        return ResponseEntity.noContent().build();
    }
}

