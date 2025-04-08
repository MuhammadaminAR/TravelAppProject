package org.example.travelappproject.controller;

import org.example.travelappproject.service.FilterUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/filter")
public class FilterUtilController {

    private final FilterUtil filterUtil;

    public FilterUtilController(FilterUtil filterUtil) {
        this.filterUtil = filterUtil;
    }

    @GetMapping
    public ResponseEntity<?> getFilter(@RequestParam String keyword) {
        return filterUtil.getDestinationsBySearchKeyword(keyword); // bu filter searchning filteri
    }
}
