package org.example.travelappproject.controller;

import lombok.RequiredArgsConstructor;
import org.example.travelappproject.dto.GoogleUserDTO;
import org.example.travelappproject.dto.LoginDTO;
import org.example.travelappproject.dto.UserCreateDTO;
import org.example.travelappproject.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
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
            GoogleUserDTO googleUserDTO = new GoogleUserDTO(
                    email,
                    null,
                    jwt.getClaimAsString("name"),
                    jwt.getClaimAsString("given_name"),
                    jwt.getClaimAsString("family_name")
            );
            userService.registerWithGoogle(googleId, googleUserDTO);
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
            String result = String.valueOf(userService.loginWithGoogle(googleId));
            return ResponseEntity.ok(result);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/sign-in")
    public ResponseEntity<?> signIn(@RequestBody LoginDTO loginDTO) {
        return userService.loginUser(loginDTO);
    }

    @PostMapping("/sign-out")
    public ResponseEntity<?> logout() {
        return ResponseEntity.ok("You successfully logged out");
    }




    class JwtResponse {
        private String token;

        public JwtResponse(String token) {
            this.token = token;
        }

        public String getToken() {
            return token;
        }
    }
}