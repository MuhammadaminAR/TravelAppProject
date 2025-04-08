package org.example.travelappproject.controller;

import org.example.travelappproject.service.DestinationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/destination")
public class DestinationController {

    private final DestinationService destinationService;

    public DestinationController(DestinationService destinationService) {
        this.destinationService = destinationService;
    }

    @GetMapping("/filter")
    public ResponseEntity<?> getDestinationsByContinentName(@RequestParam String continent) {
        return destinationService.getDestinations(continent); // bu filter boshidagi filter
    }


}
