package com.sarkhan.dtssystem.model.company.data;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PropertyLaw {

    @NotBlank(message = "Biznes əməliyyatları boş ola bilməz")
    String businessOperations;
    @NotBlank(message = "Hüquqi növ boş ola bilməz")
    String companyLawType;

    @Size(min = 1, max = 150, message = "Məhsullar 1 ilə 150 simvol arasında olmalıdır")
    @NotBlank(message = "Məhsullar boş ola bilməz")
    String products;

    @NotNull(message = "İxrac fəaliyyəti seçilməlidir")
    boolean exportActivity;

    @NotNull(message = "İxrac fəaliyyəti seçilməlidir")
    String exportBazaar;
}
