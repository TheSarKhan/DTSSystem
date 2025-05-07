package com.sarkhan.dtssystem.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserRequest {

      @NotBlank(message = "Username cannot be blank")
      @Size(min = 3, max = 20, message = "Username must be between 3 and 20 characters")
      String username;

      @NotBlank(message = "Email cannot be blank")
      @Email(message = "Invalid email format")
      String email;
      @NotBlank(message = "Name and Surname cannot be empty")
      String nameAndSurname;

      @NotBlank(message = "Phone number cannot be blank")
      @Size(min = 10, max = 15, message = "Phone number must be between 10 and 15 digits")
      String phoneNumber;
}
