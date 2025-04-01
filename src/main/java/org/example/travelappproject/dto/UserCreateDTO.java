package org.example.travelappproject.dto;

import lombok.Value;

@Value
public class UserCreateDTO {
    String email;
    String password;
    String fullName;
    String confirmPassword;
}
