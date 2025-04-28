package com.sarkhan.dtssystem.model.company.data;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CompanySize {

    @NotBlank(message = "Worker count cannot be blank")
    String workerCount;

    @NotBlank(message = "Annual turnover cannot be blank")
    @PositiveOrZero(message = "Annual turnover must be a positive number or zero")
    String annualTurnover;
}
