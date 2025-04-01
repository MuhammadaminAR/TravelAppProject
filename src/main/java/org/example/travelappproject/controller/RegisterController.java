package org.example.travelappproject.controller;


import io.swagger.v3.oas.annotations.Operation;
import org.example.travelappproject.dto.RegisterDTO;
import org.example.travelappproject.service.RegisterService;
import org.springframework.http.HttpEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/register")
public class RegisterController {
    private final RegisterService registerService;

    public RegisterController(RegisterService registerService) {
        this.registerService = registerService;
    }

    @Operation(summary = "Register a new user", description = "to register new user")
    @GetMapping
    public HttpEntity<?> register(RegisterDTO registerDTO){
        return registerService.register(registerDTO);
    }

    @Operation(summary = "Register a new OAuth2 user", description = "to register new user")
    @GetMapping("/oauth2")
    public HttpEntity<?> registerOAuth2(@AuthenticationPrincipal OAuth2User user, @RequestParam String password) {
        return registerService.registerOAuth2(user, password);
    }
}
