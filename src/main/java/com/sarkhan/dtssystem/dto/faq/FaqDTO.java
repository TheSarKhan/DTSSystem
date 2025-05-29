package com.sarkhan.dtssystem.dto.faq;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FaqDTO {
    String question;
    String answer;
}
