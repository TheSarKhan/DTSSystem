package com.sarkhan.dtssystem.model.company.data;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CompanySize {

    @NotBlank(message = "İşçi sayı boş ola bilməz")
    String workerCount;

    @NotBlank(message = "İllik dövriyyə boş ola bilməz")
     String annualTurnover;

}
