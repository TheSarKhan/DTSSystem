package com.sarkhan.dtssystem.model.company.data;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PropertyLaw {
    @NotBlank(message = "Biznes əməliyyatları boş ola bilməz")
    String businessOperations;

    @NotBlank(message = "Hüquqi növ boş ola bilməz")
    String companyLawType;

    @Size(min = 3, message = "Məhsullar minimum 3 simvol olmalıdır")
    @NotBlank(message = "Məhsullar boş ola bilməz")
    String products;

    @NotNull(message = "İxrac fəaliyyəti seçilməlidir")
    Boolean exportActivity;

    @NotEmpty(message = "İxrac bazarı boş ola bilməz")
    List<String> exportBazaar;

}
