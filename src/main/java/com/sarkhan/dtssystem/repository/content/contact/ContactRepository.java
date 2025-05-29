package com.sarkhan.dtssystem.repository.content.contact;

import com.sarkhan.dtssystem.model.content.contact.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactRepository extends JpaRepository<Contact,Long> {
}
