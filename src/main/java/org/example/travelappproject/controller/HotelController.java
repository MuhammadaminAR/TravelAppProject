package org.example.travelappproject.controller;

import org.example.travelappproject.dto.HotelDTO;
import org.example.travelappproject.dto.SearchHotelDTO;
import org.example.travelappproject.entity.Hotel;
import org.example.travelappproject.service.HotelService;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/hotel")
public class HotelController {

// nna
    private final HotelService hotelService;
    private final ResourceLoader resourceLoader;

    public HotelController(HotelService hotelService, ResourceLoader resourceLoader) {
        this.hotelService = hotelService;
        this.resourceLoader = resourceLoader;
    }

    @GetMapping("/search")
    public ResponseEntity<?> searchHotel(SearchHotelDTO searchHotelDTO) {
       return hotelService.searchHotel(searchHotelDTO);
    }

    @GetMapping
    public ResponseEntity<?> getAllHotels() {
        List<Hotel> allHotels = hotelService.getAllHotels();
        List<HotelDTO> allHotelsDTO = new ArrayList<>();

        for (Hotel allHotel : allHotels) {
            HotelDTO hotelDTO = new HotelDTO();
            hotelDTO.setHotelName(allHotel.getName());
            allHotelsDTO.add(hotelDTO);
        }
        return ResponseEntity.ok(allHotelsDTO);
    }

    @GetMapping("/city")
    public ResponseEntity<?> getAllHotelsByCity(@RequestParam Integer city) {
        List<HotelDTO> hotelDTOList = hotelService.getHotelsByCityId(city);
        for (HotelDTO hotelDTO : hotelDTOList) {
            System.out.println(hotelDTO.getHotelName());
        }
        return ResponseEntity.ok().body(hotelDTOList);
    }
}
