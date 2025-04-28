package com.sarkhan.dtssystem.model.company.data;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class IndustrialBusinessOperations {

    @NotBlank(message = "Sector cannot be blank")
    String sector;

    @Size(min = 1, max = 150, message = "Company type must be between 1 and 100 characters")
    @NotBlank(message = "Products cannot be blank")
    String products;

    @NotNull(message = "Export activity status cannot be null")
    boolean exportActivity;

     String exportBazaar;
}
