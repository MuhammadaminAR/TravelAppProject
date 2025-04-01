package org.example.travelappproject.controller;

import lombok.RequiredArgsConstructor;
import org.example.travelappproject.ai.AIService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/search")
@RequiredArgsConstructor
public class SearchController {

    private final AIService service;

    @GetMapping()
    public ResponseEntity<?> search(@RequestParam String search) {
        search+=". According to my side, it is located in the location with comma, gimme only name of locations";
        String response = service.getResponse(search);
        System.out.println(response);
        return ResponseEntity.ok(200);
    }
}
