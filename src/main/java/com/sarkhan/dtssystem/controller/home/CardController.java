package com.sarkhan.dtssystem.controller.home;

import com.sarkhan.dtssystem.dto.home.CardDTO;
import com.sarkhan.dtssystem.model.content.home.data.Card;
import com.sarkhan.dtssystem.service.home.service.CardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/cards")
@RequiredArgsConstructor
public class CardController {
    private final CardService cardService;

    @GetMapping
    public List<Card> getAll() {
        return cardService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Card> getById(@PathVariable Long id) {
        return cardService.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Card create(@RequestPart("CardRequest") CardDTO cardDTO, MultipartFile icon) throws IOException {
        return cardService.create(cardDTO,icon);
    }

    @PutMapping("/{id}")
    public Card update(@PathVariable Long id, @RequestPart("CardRequest") CardDTO cardDTO,MultipartFile icon) throws IOException {
        return cardService.update(id, cardDTO,icon);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        cardService.delete(id);
    }
}
