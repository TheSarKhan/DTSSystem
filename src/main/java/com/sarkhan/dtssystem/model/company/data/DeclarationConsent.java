package com.sarkhan.dtssystem.model.company.data;

import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DeclarationConsent {

    @NotNull(message = "Data real consent cannot be null")
    boolean dataIsReal;

    @NotNull(message = "Permit contact consent cannot be null")
    boolean permitContact;
}
