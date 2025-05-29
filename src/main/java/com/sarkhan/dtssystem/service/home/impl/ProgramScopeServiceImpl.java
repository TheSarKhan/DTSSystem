package com.sarkhan.dtssystem.service.home.impl;


import com.sarkhan.dtssystem.dto.home.ProgramScopeDTO;
import com.sarkhan.dtssystem.model.content.home.data.ProgramScope;
import com.sarkhan.dtssystem.repository.content.home.ProgramScopeRepository;
import com.sarkhan.dtssystem.service.home.service.ProgramScopeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProgramScopeServiceImpl implements ProgramScopeService {
    private final ProgramScopeRepository programScopeRepository;

    @Override
    public List<ProgramScope> getAll() {
        return programScopeRepository.findAll();
    }

    @Override
    public Optional<ProgramScope> getById(Long id) {
        return programScopeRepository.findById(id);
    }

    @Override
    public ProgramScope create(ProgramScopeDTO programScopeDTO) {
        ProgramScope programScope = new ProgramScope();
        programScope.setDataOrder(programScopeDTO.getDataOrder());
        programScope.setDescription(programScopeDTO.getDescription());
        programScope.setHeader(programScopeDTO.getHeader());
        return programScopeRepository.save(programScope);
    }

    @Override
    public ProgramScope update(Long id, ProgramScopeDTO programScopeDTO) {
        if (!programScopeRepository.existsById(id)) {
            throw new RuntimeException("ProgramScope not found");
        }
        ProgramScope programScope = programScopeRepository.findById(id).orElseThrow(() -> new RuntimeException("Istiqamət tapılmadı!"));
        programScope.setDataOrder(programScopeDTO.getDataOrder());
        programScope.setDescription(programScopeDTO.getDescription());
        programScope.setHeader(programScopeDTO.getHeader());

        return programScopeRepository.save(programScope);
    }

    @Override
    public void delete(Long id) {
        programScopeRepository.deleteById(id);
    }
}