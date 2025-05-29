package com.sarkhan.dtssystem.service.home.service;

import com.sarkhan.dtssystem.dto.home.ProgramScopeDTO;
import com.sarkhan.dtssystem.model.content.home.data.ProgramScope;

import java.util.List;
import java.util.Optional;

public interface ProgramScopeService {
    List<ProgramScope> getAll();
    Optional<ProgramScope> getById(Long id);
    ProgramScope create(ProgramScopeDTO programScope);
    ProgramScope update(Long id, ProgramScopeDTO programScope);
    void delete(Long id);
}