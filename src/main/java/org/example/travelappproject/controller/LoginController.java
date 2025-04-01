package org.example.travelappproject.controller;


import io.swagger.v3.oas.annotations.Operation;
import org.example.travelappproject.dto.LoginDTO;
import org.example.travelappproject.dto.RegisterDTO;
import org.example.travelappproject.service.LoginService;
import org.example.travelappproject.service.RegisterService;
import org.springframework.http.HttpEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
public class LoginController {

    private final LoginService loginService;

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @Operation(summary = "Login user", description = "to login user")
    @GetMapping
    public HttpEntity<?> login(@RequestBody LoginDTO loginDTO){
        return loginService.login(loginDTO);
    }

    @Operation(summary = "Login OAuth2 user", description = "to log in existed user")
    @GetMapping("/oauth2")
    public HttpEntity<?> loginAuth2(@AuthenticationPrincipal OAuth2User user) {
        return loginService.loginOAuth2(user);
    }
}
