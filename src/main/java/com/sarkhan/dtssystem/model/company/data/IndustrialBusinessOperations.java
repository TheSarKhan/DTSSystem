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

    @NotBlank(message = "Sektor boş ola bilməz")
    String sector;

    @Size(min = 1, max = 150, message = "Məhsullar 1 ilə 150 simvol arasında olmalıdır")
    @NotBlank(message = "Məhsullar boş ola bilməz")
    String products;

    @NotNull(message = "İxrac fəaliyyəti seçilməlidir")
    boolean exportActivity;


    String exportBazaar;
}
