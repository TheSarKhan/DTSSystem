package com.sarkhan.dtssystem.model.company.data;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FinancialNeeding {

    @NotNull(message = "Maliyyə ehtiyacı razılığı seçilməlidir")
    boolean financialNeed;

     String neededBudget;
}
