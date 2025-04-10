package org.example.travelappproject.dto;

import lombok.Value;

import java.time.LocalDate;

@Value
public class SearchHotelDTO {
    String cityName;
    LocalDate checkInDate;
    LocalDate checkOutDate; //dd
    Integer guestsCount;
    Integer roomsCount;
    String roomStatus;

}
