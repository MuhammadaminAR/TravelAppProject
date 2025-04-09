package org.example.travelappproject.controller;

import lombok.RequiredArgsConstructor;
import org.example.travelappproject.dto.LoginDTO;
import org.example.travelappproject.dto.UserCreateDTO;
import org.example.travelappproject.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;

    @PostMapping("/sign-up")
    public ResponseEntity<?> signUp(@RequestBody UserCreateDTO userCreateDTO) {
        userService.registerUser(userCreateDTO);
        return ResponseEntity.ok().body(200);
    }

    @PostMapping("/google/sign-up")
    public ResponseEntity<?> googleSignUp(Authentication authentication) {
        try {
            Jwt jwt = (Jwt) authentication.getPrincipal();
            String googleId = jwt.getSubject();
            String email = jwt.getClaimAsString("email");
            if (email == null) {
                return ResponseEntity.badRequest().body("Email is required");
            }

            UserCreateDTO userCreateDTO = new UserCreateDTO();
            userCreateDTO.setEmail(email);
            userCreateDTO.setName(jwt.getClaimAsString("name"));
            userCreateDTO.setGivenName(jwt.getClaimAsString("given_name"));
            userCreateDTO.setFamilyName(jwt.getClaimAsString("family_name"));
            userCreateDTO.setPassword(null);

            userService.registerWithGoogle(googleId, userCreateDTO);
            return ResponseEntity.ok("Google user registered successfully: " + email);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/google/sign-in")
    public ResponseEntity<?> googleSignIn(Authentication authentication) {
        try {
            Jwt jwt = (Jwt) authentication.getPrincipal();
            String googleId = jwt.getSubject();
            String email = jwt.getClaimAsString("email");
            if (email == null) {
                return ResponseEntity.badRequest().body("Email is required");
            }

            // Verify user exists and sign them in
            String result = userService.signInWithGoogle(googleId);
            return ResponseEntity.ok(result);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/sign-in")
    public ResponseEntity<?> signIn(@RequestBody LoginDTO loginDTO) {
        return userService.loginUser(loginDTO);
    }
}