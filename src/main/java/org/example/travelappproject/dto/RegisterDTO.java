package org.example.travelappproject.dto;


import lombok.Data;
import lombok.Value;

@Value
public class RegisterDTO {
    String fullName;
    String email;
    String password;
}
