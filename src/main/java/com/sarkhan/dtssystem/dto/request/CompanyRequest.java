package com.sarkhan.dtssystem.dto.request;

import jakarta.validation.constraints.*; // ya da javax.validation.constraints.*; projenin sürümüne göre
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CompanyRequest {

    @NotBlank(message = "Company name cannot be blank")
    @Size(max = 100, message = "Company name must be at most 100 characters")
    String companyName;

    @NotBlank(message = "Company register number cannot be blank")
    @Pattern(regexp = "\\d{6,12}", message = "Register number must be between 6 and 12 digits")
    String companyRegisterNumber;

    @Min(value = 1800, message = "Create year must be no earlier than 1800")
    @Max(value = 2100, message = "Create year must be no later than 2100")
    int createYear;

    @NotBlank(message = "Address cannot be blank")
    @Size(max = 255, message = "Address must be at most 255 characters")
    String address;

    @NotBlank(message = "City and region cannot be blank")
    @Size(max = 100, message = "City and region must be at most 100 characters")
    String cityAndRegion;

     @Pattern(regexp = "^(https?://)?(www\\.)?[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}(/.*)?$",
            message = "Invalid website URL")
    String website;

    @NotBlank(message = "Contact name cannot be blank")
    @Size(max = 100, message = "Contact name must be at most 100 characters")
    String contactName;

    @NotBlank(message = "Contact email cannot be blank")
    @Email(message = "Invalid email format")
    String contactEmail;

    @NotBlank(message = "Contact phone cannot be blank")
    @Pattern(regexp = "^\\+?[0-9\\-\\s]{7,20}$", message = "Invalid phone number")
    String contactPhone;
}
