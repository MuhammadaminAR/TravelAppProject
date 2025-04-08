package org.example.travelappproject.controller;

import lombok.RequiredArgsConstructor;
import org.example.travelappproject.entity.User;
import org.example.travelappproject.entity.VerificationCode;
import org.example.travelappproject.repo.UserRepository;
import org.example.travelappproject.repo.VerificationCodeRepository;
import org.example.travelappproject.service.EmailService;
import org.example.travelappproject.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/email")
public class EmailController {

    private final EmailService emailService;
    private final UserService userService;
    private final UserRepository userRepository;
    private final VerificationCodeRepository verificationCodeRepository;

    @PostMapping("/forget")
    public ResponseEntity<?> forgetPassword( @RequestParam String email) {
        System.out.println("email: " + email);
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new RuntimeException("User not found");
        }

        String verificationCode = String.format("%06d", new Random().nextInt(999999));
        String subject = "Password Reset Verification Code";
        String message = "Your verification code is: " + verificationCode +
                "\nThis code is valid for 10 minutes.";
        ResponseEntity<?> response = emailService.sendEmail(email, subject, message);

        VerificationCode verificationCodeDB = VerificationCode.builder()
                .code(verificationCode)
                .createdAt(LocalDateTime.now())
                .expiresAt(LocalDateTime.now().plusMinutes(10))
                .build();
        verificationCodeRepository.save(verificationCodeDB);
        if (response.getStatusCode().is2xxSuccessful()) {
            return ResponseEntity.ok("Sent code to email");
        } else {
            return response;
        }
    }

    @PostMapping("/code")
    public ResponseEntity<?> checkCode(@RequestParam String code) {
        List<VerificationCode> verificationCodes = verificationCodeRepository.findAll();
        for (VerificationCode verificationCode : verificationCodes) {
            if (verificationCode.getExpiresAt().isAfter(LocalDateTime.now())) {
                if (verificationCode.getCode().equals(code)) {
                    verificationCodeRepository.delete(verificationCode);
                    return ResponseEntity.ok("Verification code is valid");
                }
            }
        }
        return ResponseEntity.ok("Verification code is not valid");
    }
}