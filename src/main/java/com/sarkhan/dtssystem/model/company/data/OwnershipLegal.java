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

    @NotBlank(message = "Şirkət tipi boş ola bilməz")
    String companyType;

    @NotNull(message = "Faiz dəyəri boş olmamalıdır")
    @Positive(message = "Faiz dəyəri müsbət olmalıdır")
    double percentage;

    @Size(min = 1, max = 200, message = "Sahiblər 1 ilə 200 simvol arasında olmalıdır")
    String companyOwners;

}
