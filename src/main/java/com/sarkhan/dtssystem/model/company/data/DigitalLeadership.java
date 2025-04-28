package com.sarkhan.dtssystem.model.company.data;

import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DigitalLeadership {

    @NotNull(message = "Digital team or lead status cannot be null")
    boolean digitalTeamOrLead;

    @NotNull(message = "Digital path status cannot be null")
    boolean digitalPath;

    @NotNull(message = "Digital transformation loyalty status cannot be null")
    boolean digitalTransformationLoyality;
}
