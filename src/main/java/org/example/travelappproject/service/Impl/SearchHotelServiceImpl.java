package org.example.travelappproject.service.Impl;

import lombok.RequiredArgsConstructor;
import org.example.travelappproject.dto.HotelDTO;
import org.example.travelappproject.dto.SearchHotelDTO;
import org.example.travelappproject.entity.City;
import org.example.travelappproject.entity.Hotel;
import org.example.travelappproject.repo.CityRepository;
import org.example.travelappproject.repo.HotelRepository;
import org.example.travelappproject.service.HotelService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SearchHotelServiceImpl implements HotelService {

    private final CityRepository cityRepository;

    @Override
    public List<Hotel> getAllHotels() {
        return hotelRepository.findAll();
    }

    @Override
    public Hotel createHotel() {
        return null;
    }

    @Override
    public List<HotelDTO> getHotelsByCityId(Integer cityId) {
        Optional<City> optionalCity = cityRepository.findById(cityId);
        List<HotelDTO> hotelDTOList = new ArrayList<>();
        if (optionalCity.isPresent()) {
            City city = optionalCity.get();
            List<Hotel> hotels = hotelRepository.findByCity(city);
            for (Hotel hotel : hotels) {
                HotelDTO hotelDTO = new HotelDTO();
                hotelDTO.setHotelName(hotel.getName());
                hotelDTO.setCityName(city.getName());
                hotelDTOList.add(hotelDTO);
            }
        }
        return hotelDTOList;
    }

    @Override
    public ResponseEntity<?> searchHotel(SearchHotelDTO searchHotelDTO) {
        List<Hotel> hotels = hotelRepository.searchAvailableHotels(
                searchHotelDTO.getCityName(),
                searchHotelDTO.getCheckInDate(),
                searchHotelDTO.getCheckOutDate(),
                searchHotelDTO.getGuestsCount(),
                searchHotelDTO.getRoomsCount(),
                searchHotelDTO.getRoomStatus()
        );
        List<HotelDTO> hotelDTOS = new ArrayList<>();
        for (Hotel hotel : hotels) {
            HotelDTO hotelDTO= new HotelDTO();
            hotelDTO.setHotelName(hotel.getName());
            hotelDTO.setAccommodationType(hotel.getAccommodationType());
            hotelDTO.setDescription(hotel.getDescription());
            hotelDTO.setPhotos(hotel.getPhotos());
            hotelDTO.setCityName(hotel.getCity().getName());
            hotelDTO.setAmenities(hotel.getAmenities());
            hotelDTOS.add(hotelDTO);
        }
        return ResponseEntity.ok(hotelDTOS);
    }

    private final HotelRepository hotelRepository;

}
