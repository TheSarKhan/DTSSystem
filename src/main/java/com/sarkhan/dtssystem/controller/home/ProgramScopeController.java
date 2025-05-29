package com.sarkhan.dtssystem.controller.home;

import com.sarkhan.dtssystem.dto.home.ProgramScopeDTO;
import com.sarkhan.dtssystem.model.content.home.data.ProgramScope;
import com.sarkhan.dtssystem.service.home.service.ProgramScopeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/program-scopes")
@RequiredArgsConstructor
public class ProgramScopeController {
    private final ProgramScopeService programScopeService;

    @GetMapping
    public List<ProgramScope> getAll() {
        return programScopeService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProgramScope> getById(@PathVariable Long id) {
        return programScopeService.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ProgramScope create(@RequestBody ProgramScopeDTO programScopeDTO) {
        return programScopeService.create(programScopeDTO);
    }

    @PutMapping("/{id}")
    public ProgramScope update(@PathVariable Long id, @RequestBody ProgramScopeDTO programScopeDTO) {
        return programScopeService.update(id, programScopeDTO);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        programScopeService.delete(id);
    }
}
