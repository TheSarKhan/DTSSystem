package com.sarkhan.dtssystem.model.company.data;

import jakarta.validation.constraints.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CompanyData {
    @NotBlank(message = "Şirkətin adı boş ola bilməz")
    @NotNull(message = "Şirkətin adı boş olmamalıdır")
    @Size(max = 30, message = "Şirkətin adı maksimum 30 simvol ola bilər")
    String companyName;

    @NotBlank(message = "Qeydiyyat nömrəsi boş ola bilməz")
    @Pattern(regexp = "\\d{6,12}", message = "Qeydiyyat nömrəsi 6 ilə 12 rəqəm arasında olmalıdır")
    String companyRegisterNumber;

    @Min(value = 1800, message = "Yaranma ili 1800-dən əvvəl ola bilməz")
    @Max(value = 2100, message = "Yaranma ili 2100-dən böyük ola bilməz")
    int createYear;
    @NotBlank(message = "İşçi sayı boş ola bilməz")
    String workerCount;

    @NotBlank(message = "İllik dövriyyə boş ola bilməz")
    String annualTurnover;

    @NotBlank(message = "Ünvan boş ola bilməz")
    @Size(max = 255, message = "Ünvan maksimum 255 simvol ola bilər")
    String address;

    @NotBlank(message = "Şəhər və region boş ola bilməz")
    @Size(max = 100, message = "Şəhər və region maksimum 100 simvol ola bilər")
    String cityAndRegion;

    @Pattern(regexp = "^(https?://)?(www\\.)?[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}(/.*)?$", message = "Sayt ünvanı yanlışdır")
    String website;

    @NotBlank(message = "Əlaqə adı boş ola bilməz")
    @Size(max = 100, message = "Əlaqə adı maksimum 100 simvol ola bilər")
    String contactName;

    @NotBlank(message = "Əlaqə emaili boş ola bilməz")
    @Email(message = "Email formatı yanlışdır")
    String contactEmail;

    @NotBlank(message = "Əlaqə nömrəsi boş ola bilməz")
    @Pattern(regexp = "^\\+?[0-9\\-\\s]{7,20}$", message = "Telefon nömrəsi yanlışdır")
    String contactPhone;

}
