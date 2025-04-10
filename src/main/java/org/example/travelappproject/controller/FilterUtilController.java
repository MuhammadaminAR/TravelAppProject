package org.example.travelappproject.controller;

import org.example.travelappproject.repo.DestinationRepository;
import org.example.travelappproject.service.DestinationService;
import org.example.travelappproject.service.FilterUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/filter")
public class FilterUtilController {

    private final FilterUtil filterUtil;
    private final DestinationRepository destinationRepository;
    private final DestinationService destinationService;

    public FilterUtilController(FilterUtil filterUtil, DestinationRepository destinationRepository, DestinationService destinationService) {
        this.filterUtil = filterUtil;
        this.destinationRepository = destinationRepository;
        this.destinationService = destinationService;
    }

    @GetMapping
    public ResponseEntity<?> getFilter(@RequestParam String keyword) {
        return filterUtil.getDestinationsBySearchKeyword(keyword); // bu filter searchning filteri
    }

    @GetMapping("/{destinationId}")
    public ResponseEntity<?> getDestinationName(@PathVariable int destinationId) {
        return destinationService.getDestinationName(destinationId);


    }
}
