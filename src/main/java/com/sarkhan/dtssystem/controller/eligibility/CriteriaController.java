package com.sarkhan.dtssystem.controller.eligibility;

import com.sarkhan.dtssystem.dto.eligibility.CriteriaDTO;
import com.sarkhan.dtssystem.model.content.eligibility.data.Criteria;
import com.sarkhan.dtssystem.service.eligibility.service.CriteriaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/criteria")
@RequiredArgsConstructor
public class CriteriaController {

    private final CriteriaService criteriaService;

    @PostMapping
    public ResponseEntity<Criteria> create(@RequestPart CriteriaDTO criteriaDTO, MultipartFile icon) throws IOException {
        return ResponseEntity.ok(criteriaService.create(criteriaDTO,icon));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Criteria> getById(@PathVariable Long id) {
        return ResponseEntity.ok(criteriaService.getById(id));
    }

    @GetMapping
    public ResponseEntity<List<Criteria>> getAll() {
        return ResponseEntity.ok(criteriaService.getAll());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Criteria> update(@PathVariable Long id, @RequestPart CriteriaDTO criteriaDTO,MultipartFile icon) throws IOException {
        return ResponseEntity.ok(criteriaService.update(id, criteriaDTO,icon));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        criteriaService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
