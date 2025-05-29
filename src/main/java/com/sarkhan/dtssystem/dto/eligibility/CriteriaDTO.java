package com.sarkhan.dtssystem.dto.eligibility;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CriteriaDTO {
    String header;
    String description;
}
