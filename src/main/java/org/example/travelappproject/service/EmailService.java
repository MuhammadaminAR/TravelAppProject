package org.example.travelappproject.service;

import org.springframework.http.ResponseEntity;

public interface EmailService {

    ResponseEntity<?> sendEmail(String to, String subject, String text);
}
