package org.example.travelappproject.controller;

import lombok.RequiredArgsConstructor;
import org.example.travelappproject.dto.FacebookDTO;
import org.example.travelappproject.dto.GoogleUserDTO;
import org.example.travelappproject.dto.LoginDTO;
import org.example.travelappproject.dto.UserCreateDTO;
import org.example.travelappproject.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
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
    public ResponseEntity<?> googleSignUp(@AuthenticationPrincipal OAuth2User principal) {
        try {
            String email = principal.getAttribute("email");
            String googleId = principal.getAttribute("sub");
            GoogleUserDTO googleUserDTO = new GoogleUserDTO(
                    email,
                    principal.getAttribute("name"),
                    principal.getAttribute("given_name"),
                    principal.getAttribute("family_name")
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

//    @PostMapping("/facebook/sign-in")
//    public ResponseEntity<?> facebookSignIn(@AuthenticationPrincipal OAuth2User principal) {
//        try {
//            String facebookId = principal.getAttribute("id");
//            String email = principal.getAttribute("email");
//
//            if (email == null) {
//                return ResponseEntity.badRequest().body("Email is required");
//            }
//
//         return  userService.loginWithFacebook(facebookId);
//        } catch (RuntimeException e) {
//            return ResponseEntity.badRequest().body("Facebook sign-in failed: " + e.getMessage());
//        }
//    }
//
//
//    @PostMapping("/facebook/sign-up")
//    public ResponseEntity<?> facebookSignUp(@AuthenticationPrincipal OAuth2User principal) {
//        try {
//            String email = principal.getAttribute("email");
//
//            FacebookDTO facebookUserDTO = new FacebookDTO(
//                    email,
//                    principal.getAttribute("name"),
//                    principal.getAttribute("first_name"),
//                    principal.getAttribute("last_name")
//            );
//
//            String facebookId = principal.getAttribute("id");
//            userService.registerWithFacebook(facebookId, facebookUserDTO); // reuse same method
//
//            return ResponseEntity.ok("Facebook user registered successfully: " + email);
//        } catch (Exception e) {
//            return ResponseEntity.badRequest().body("Facebook sign-up failed: " + e.getMessage());
//        }
//    }

    @PostMapping("/sign-in")
    public ResponseEntity<?> signIn(@RequestBody LoginDTO loginDTO) {
        return userService.loginUser(loginDTO);
    }

    @PostMapping("/sign-out")
    public ResponseEntity<?> logout() {
        return ResponseEntity.ok("You successfully logged out");
    }
}