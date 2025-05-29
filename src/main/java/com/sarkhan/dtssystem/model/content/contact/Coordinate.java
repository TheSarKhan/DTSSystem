package com.sarkhan.dtssystem.model.content.contact;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class Coordinate {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    Long id;
    double x;
    double y;

}