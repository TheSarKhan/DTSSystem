package com.sarkhan.dtssystem.dto.home;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CardDTO {
    String header;
    String description;
}
