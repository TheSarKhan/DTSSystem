package com.sarkhan.dtssystem.model.company.data;

import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DigitalLeadership {

    @NotNull(message = "Rəqəmsal komanda və ya rəhbər statusu seçilməlidir")
    boolean digitalTeamOrLead;

    @NotNull(message = "Rəqəmsal yol xəritəsi statusu seçilməlidir")
    boolean digitalPath;

    @NotNull(message = "Rəqəmsal transformasiya sadiqliyi seçilməlidir")
    boolean digitalTransformationLoyality;

}
