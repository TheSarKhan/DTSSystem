package com.sarkhan.dtssystem.service.contact.service;

import com.sarkhan.dtssystem.dto.contact.ContactDTO;
import com.sarkhan.dtssystem.dto.faq.FaqDTO;
import com.sarkhan.dtssystem.model.content.contact.Contact;
import com.sarkhan.dtssystem.model.content.faq.FAQ;
import io.jsonwebtoken.io.IOException;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ContactService {
    Contact create(ContactDTO dto, MultipartFile icon) throws IOException, java.io.IOException;


    Contact getById(Long id);

    List<Contact> getAll();


    Contact update(Long id, ContactDTO dto, MultipartFile icon) throws IOException, java.io.IOException;

    void delete(Long id);
}
