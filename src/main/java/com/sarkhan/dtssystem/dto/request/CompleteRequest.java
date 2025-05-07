package com.sarkhan.dtssystem.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CompleteRequest {

    @NotBlank(message = "Email cannot be empty")
    @Email(message = "Invalid email format")
    String email;
    @NotBlank(message = "Name and Surname cannot be empty")
    String nameAndSurname;


    @NotBlank(message = "Phone number cannot be empty")
    String phoneNumber;

    @NotBlank(message = "Password cannot be empty")
    @Size(min = 6, message = "Password must be at least 6 characters")
    String password;

    @NotBlank(message = "Confirm password cannot be empty")
    String confirmPassword;
}
