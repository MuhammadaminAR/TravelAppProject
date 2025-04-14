package org.example.travelappproject.dto;


import lombok.Value;

@Value
public class GoogleUserDTO {
    String email;
    String name;
    String givenName;
    String familyName;
}
