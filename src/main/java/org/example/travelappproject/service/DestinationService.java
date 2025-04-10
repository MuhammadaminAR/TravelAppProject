package org.example.travelappproject.service;

import org.springframework.http.ResponseEntity;

public interface DestinationService {

    ResponseEntity<?> getDestinations(String continent);// Interface destination uchun

    ResponseEntity<?> getDestinationName(int destinationId);

}
