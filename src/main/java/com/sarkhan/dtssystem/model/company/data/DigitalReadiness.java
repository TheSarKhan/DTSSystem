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
public class DigitalReadiness {
    List<String> keyChallenges;

    @NotNull(message = "Rəqəmsal səviyyə boş olmamalıdır")
    byte digitalLevel;

    @NotEmpty(message = "İstifadə olunan rəqəmsal alətlər boş ola bilməz")
    List<String> digitalTools;

    @Size(min = 1, max = 100, message = "Şirkətin məqsədi 1 ilə 100 simvol arasında olmalıdır")
    @NotBlank(message = "Şirkətin məqsədi boş ola bilməz")
    String companyPurpose;

}
