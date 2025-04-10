package org.example.travelappproject.dto;


import lombok.Value;

@Value
public class GoogleUserDTO {
    String email;
    String password;
    String name;
    String givenName;
    String familyName;
}
