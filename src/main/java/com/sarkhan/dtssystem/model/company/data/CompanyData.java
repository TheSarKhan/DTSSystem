package com.sarkhan.dtssystem.model.company.data;

import jakarta.validation.constraints.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CompanyData {
    @NotBlank(message = "Şirkətin adı boş ola bilməz")
    @NotNull(message = "Şirkətin adı boş olmamalıdır")
    @Size(max = 255, message = "Şirkətin adı maksimum 255 simvol ola bilər")
    String companyName;

    @NotBlank(message = "Qeydiyyat nömrəsi boş ola bilməz")
    @Min(value = 20, message = "Qeydiyyat nömrəsi minimum 20 simvol olmalıdır")
    String companyRegisterNumber;
     @Min(value = 1900, message = "Yaranma ili 1900-dən əvvəl ola bilməz")
     int createYear;

    @NotBlank(message = "Tam ştatlı işçilərin sayı boş buraxıla bilməz və seçimlərdən biri mütləq seçilməlidir.")
    String workerCount;

    @NotBlank(message = "Zəhmət olmasa illik dövriyyəni seçin.")
    String annualTurnover;

    @NotBlank(message = "Ünvan boş ola bilməz")
    @Size(max = 255, message = "Şirkət ünvanı ən çox 255 simvoldan ibarət olmalıdır")
    String address;

    @NotBlank(message = "Şəhər və ya regionu daxil edin")
     String cityAndRegion;

    @Pattern(regexp = "^(https?://)(www\\.)?[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}(/.*)?$", message = "Sayt ünvanı yanlışdır")
    String website;


    @NotBlank(message = "Əlaqələndirici şəxs adı tələb olunur zəhmət olmasa şəxsin adın daxil edin.")
    @Size(max = 100,min = 2, message = "Əlaqələndirici şəxs adı minimum 2 maksimum 100 simvol ola bilər")
    String contactName;

    @NotBlank(message = "Əlaqə emaili boş ola bilməz")
    @Email(message = "Email formatı yanlışdır")
    @Pattern(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$", message = "Zəhmət olmasa düzgün email ünvanı daxil edin.")
    String contactEmail;


    @NotBlank(message = "Əlaqə nömrəsi boş ola bilməz")
    @Pattern(
            regexp = "^(?=(?:.*\\d){12,})\\+?[0-9\\s\\-()]{10,20}$",
            message = "Telefon nömrəsi düzgün formatda daxil edilməlidir."
    )
    String contactPhone;


}
