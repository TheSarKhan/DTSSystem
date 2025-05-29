package com.sarkhan.dtssystem.dto.eligibility;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MetadologyDTO {
    String header;
    String description;
}
