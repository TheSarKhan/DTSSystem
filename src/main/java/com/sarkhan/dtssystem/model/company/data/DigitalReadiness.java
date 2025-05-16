package com.sarkhan.dtssystem.model.company.data;

import jakarta.validation.constraints.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DigitalReadiness {
    List<String> keyChallenges;

    @NotNull(message = "Rəqəmsal səviyyə boş olmamalıdır")
    @Min(value = 1, message = "Rəqəmsal səviyyə minimum 1 olmalıdır")
    @Max(value = 5, message = "Rəqəmsal səviyyə maksimum 5 olmalıdır")
    private Byte digitalLevel;

    @NotEmpty(message = "İstifadə olunan rəqəmsal alətlər boş ola bilməz")
    List<String> digitalTools;

    @NotBlank(message = "Şirkətin məqsədi boş ola bilməz")
    @Size(max = 100, message = "Şirkətin məqsədi maksimum 100 simvol ola bilər")
    String companyPurpose;


}
