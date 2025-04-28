package com.sarkhan.dtssystem.model.company.data;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DigitalReadiness {

    @NotNull(message = "Digital level cannot be null")
    byte digitalLevel;

    @NotBlank(message = "Digital tools cannot be blank")
    String digitalTools;

     List<String> keyChallenges;
    @Size(min = 1, max = 50, message = "Company type must be between 1 and 100 characters")

    @NotBlank(message = "Company purpose cannot be blank")
    String companyPurpose;
}
