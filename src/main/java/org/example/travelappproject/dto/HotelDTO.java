package org.example.travelappproject.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.Value;
import org.example.travelappproject.entity.Amenity;
import org.example.travelappproject.entity.Attachment;
import org.example.travelappproject.entity.City;
import org.example.travelappproject.enums.AccommodationType;

import java.util.List;

@Getter //sdd
@Setter
public class HotelDTO {
    String hotelName;
    AccommodationType accommodationType;
    String cityName;
    String description;
    List<Amenity> amenities;
    List<Attachment> photos;
}
