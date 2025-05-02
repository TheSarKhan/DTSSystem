package com.sarkhan.dtssystem.model.company.data;

import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DeclarationConsent {
    @NotNull(message = "Məlumatların doğruluğu razılığı seçilməlidir")
    boolean dataIsReal;

    @NotNull(message = "Əlaqə icazəsi seçilməlidir")
    boolean permitContact;

}
