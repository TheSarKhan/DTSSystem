package com.sarkhan.dtssystem.model.user;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    Long id;

    String username;
    String nameSurname;
    String password;
    String email;
    String phoneNumber;
    LocalDateTime updatedDate;
    boolean isVerified;

}
