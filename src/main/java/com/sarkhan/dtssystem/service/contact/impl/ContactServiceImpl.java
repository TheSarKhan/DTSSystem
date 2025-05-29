package com.sarkhan.dtssystem.service.contact.impl;

import com.sarkhan.dtssystem.dto.contact.ContactDTO;
import com.sarkhan.dtssystem.dto.faq.FaqDTO;
import com.sarkhan.dtssystem.model.content.contact.Contact;
import com.sarkhan.dtssystem.model.content.faq.FAQ;
import com.sarkhan.dtssystem.repository.content.contact.ContactRepository;
import com.sarkhan.dtssystem.repository.content.faq.FAQRepository;
import com.sarkhan.dtssystem.service.FileStorageService;
import com.sarkhan.dtssystem.service.contact.service.ContactService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ContactServiceImpl implements ContactService {

    private final ContactRepository contactRepository;
private final FileStorageService fileStorageService;
    @Override
    public Contact create(ContactDTO dto, MultipartFile icon) throws IOException {
        Contact contact = new Contact();
        contact.setContactData(dto.getContactData());
        contact.setIconUrl(fileStorageService.saveFile(icon));

        return contactRepository.save(contact);
    }

    @Override
    public Contact getById(Long id) {

        return contactRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Contact not found"));
    }

    @Override
    public List<Contact> getAll() {
        return contactRepository.findAll();
    }

    @Override
    public Contact update(Long id, ContactDTO dto,MultipartFile icon) throws IOException {
        Contact faq = contactRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Contact not found"));
        faq.setContactData(dto.getContactData());
        faq.setIconUrl(fileStorageService.saveFile(icon));

        return contactRepository.save(faq);
    }

    @Override
    public void delete(Long id) {
        contactRepository.deleteById(id);
    }
}
