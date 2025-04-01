package org.example.travelappproject.controller;

import lombok.RequiredArgsConstructor;
import org.example.travelappproject.service.EmailService;
import org.example.travelappproject.service.Impl.EmailServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/email")
public class EmailController {

    private final EmailService emailService;

    @GetMapping("/send-email")
    public ResponseEntity<?> sendEmail(@RequestParam String to, @RequestParam String subject, @RequestParam String text) {
        return emailService.sendEmail(to, subject, text);
    }
}
