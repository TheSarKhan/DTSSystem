package com.sarkhan.dtssystem.service.home.impl;

import com.sarkhan.dtssystem.dto.home.CardDTO;
import com.sarkhan.dtssystem.mapper.HomeMapper;
import com.sarkhan.dtssystem.model.content.home.data.Card;
import com.sarkhan.dtssystem.repository.content.home.CardRepository;
import com.sarkhan.dtssystem.service.home.service.CardService;
import com.sarkhan.dtssystem.service.FileStorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CardServiceImpl implements CardService {
    @Value("${base-url}")
    private String baseUrl;

    private final CardRepository cardRepository;
private final HomeMapper homeMapper;
private final FileStorageService fileStorageService;
    @Override
    public List<Card> getAll() {
        return cardRepository.findAll();
    }

    @Override
    public Optional<Card> getById(Long id) {
        return cardRepository.findById(id);
    }

    @Override
    public Card create(CardDTO cardDTO, MultipartFile icon) throws IOException {
        Card card=new Card();
        card.setDescription(cardDTO.getDescription());
        card.setHeader(cardDTO.getHeader());
        card.setIconUrl(fileStorageService.saveFile(icon));
        return cardRepository.save(card);
    }
//baseUrl +"/uploads/"+
    @Override
    public Card update(Long id, CardDTO cardDTO,MultipartFile icon) throws IOException {
        if (!cardRepository.existsById(id)) {
            throw new RuntimeException("Card not found");
        }
        Card card = cardRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Card tapılmadı!"));
        fileStorageService.deleteFile(card.getIconUrl());
        card.setDescription(cardDTO.getDescription());
        card.setHeader(cardDTO.getHeader());
        card.setIconUrl(fileStorageService.saveFile(icon));
        return cardRepository.save(card);
    }

    @Override
    public void delete(Long id) {
        cardRepository.deleteById(id);
    }
}
