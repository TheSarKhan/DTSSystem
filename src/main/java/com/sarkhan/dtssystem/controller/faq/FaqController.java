package com.sarkhan.dtssystem.controller.faq;

import com.sarkhan.dtssystem.dto.eligibility.MetadologyDTO;
import com.sarkhan.dtssystem.dto.faq.FaqDTO;
import com.sarkhan.dtssystem.model.content.eligibility.data.Metadology;
import com.sarkhan.dtssystem.model.content.faq.FAQ;
import com.sarkhan.dtssystem.service.faq.service.FaqService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/faq")
@RequiredArgsConstructor
public class FaqController {
    private final FaqService faqService;

    @PostMapping
    public ResponseEntity<FAQ> create(@RequestBody FaqDTO faqDTO) {
        return ResponseEntity.ok(faqService.create(faqDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<FAQ> getById(@PathVariable Long id) {
        return ResponseEntity.ok(faqService.getById(id));
    }

    @GetMapping
    public ResponseEntity<List<FAQ>> getAll() {
        return ResponseEntity.ok(faqService.getAll());
    }

    @PutMapping("/{id}")
    public ResponseEntity<FAQ> update(@PathVariable Long id, @RequestBody FaqDTO faqDTO) {
        return ResponseEntity.ok(faqService.update(id, faqDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        faqService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
