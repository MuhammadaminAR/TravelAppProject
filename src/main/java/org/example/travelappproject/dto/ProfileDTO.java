package org.example.travelappproject.dto;

import lombok.Value;
import org.example.travelappproject.entity.Attachment;

@Value
public class ProfileDTO {
    String fullName;
    String location;
    String phone;
    String address;
}
