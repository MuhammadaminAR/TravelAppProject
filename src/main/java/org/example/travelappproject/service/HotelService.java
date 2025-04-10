package org.example.travelappproject.service;


import org.example.travelappproject.dto.HotelDTO;
import org.example.travelappproject.dto.SearchHotelDTO;
import org.example.travelappproject.entity.City;
import org.example.travelappproject.entity.Hotel;
import org.example.travelappproject.repo.CityRepository;
import org.example.travelappproject.repo.HotelRepository;
import org.springframework.boot.autoconfigure.security.reactive.ReactiveSecurityAutoConfiguration;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import java.util.List;

public interface HotelService {

    public List<Hotel> getAllHotels();

    public Hotel createHotel();

    public List<HotelDTO> getHotelsByCityId(Integer cityId);

    public ResponseEntity<?> searchHotel(SearchHotelDTO searchHotelDTO);
}

