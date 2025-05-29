package com.sarkhan.dtssystem.controller;


import com.sarkhan.dtssystem.dto.contact.ContactDTO;
import com.sarkhan.dtssystem.dto.contact.CoordinateDTO;
import com.sarkhan.dtssystem.model.content.contact.Contact;
import com.sarkhan.dtssystem.model.content.contact.Coordinate;
import com.sarkhan.dtssystem.repository.content.contact.CoordinateRepository;
import com.sarkhan.dtssystem.service.contact.service.ContactService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/contact")
@RequiredArgsConstructor
public class ContactController {
    private final ContactService contactService;
    private final CoordinateRepository coordinateRepository;

    @PostMapping
    public ResponseEntity<Contact> create(@RequestPart ContactDTO contactDTO, MultipartFile icon) throws IOException {
        return ResponseEntity.ok(contactService.create(contactDTO, icon));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Contact> getById(@PathVariable Long id) {
        return ResponseEntity.ok(contactService.getById(id));
    }

    @GetMapping
    public ResponseEntity<List<Contact>> getAll() {
        return ResponseEntity.ok(contactService.getAll());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Contact> update(@PathVariable Long id, @RequestPart ContactDTO contactDTO, MultipartFile icon) throws IOException {
        return ResponseEntity.ok(contactService.update(id, contactDTO, icon));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        contactService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping
    public Coordinate changeCoordinate(@RequestPart CoordinateDTO coordinateDTO) {

        Pageable topOne = PageRequest.of(0, 1);
        List<Coordinate> latestList = coordinateRepository.findLatestCoordinate(topOne);


        latestList.stream().findFirst().ifPresent(existing -> {
            coordinateRepository.deleteById(existing.getId());
        });


        Coordinate newCoordinate = mapDtoToEntity(coordinateDTO);


        return coordinateRepository.save(newCoordinate);
    }


    private Coordinate mapDtoToEntity(CoordinateDTO dto) {
        Coordinate coordinate = new Coordinate();
        coordinate.setX(dto.getX());
        coordinate.setY(dto.getY());
        return coordinate;
    }

}
