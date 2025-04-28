package com.sarkhan.dtssystem.model.company.data;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OwnershipLegal {

    @NotBlank(message = "Company type cannot be blank")
    String companyType;

    @NotNull(message = "Percentage cannot be null")
    @Positive(message = "Percentage must be a positive number")
    double percentage;
    @Size(min = 1, max = 200, message = "Company owners must be between 1 and 200 characters")
     String companyOwners;
}
