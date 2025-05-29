package com.sarkhan.dtssystem.dto.contact;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CoordinateDTO {
    double x;
    double y;
}
