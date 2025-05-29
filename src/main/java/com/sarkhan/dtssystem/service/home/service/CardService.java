package com.sarkhan.dtssystem.service.home.service;

import com.sarkhan.dtssystem.dto.home.CardDTO;
import com.sarkhan.dtssystem.model.content.home.data.Card;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface CardService {
    List<Card> getAll();
    Optional<Card> getById(Long id);
    Card create(CardDTO cardDTO, MultipartFile icon) throws IOException;
    Card update(Long id, CardDTO cardDTO,MultipartFile icon) throws IOException;
    void delete(Long id);
}
