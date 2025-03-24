package org.example.travelappproject.service;

import org.example.travelappproject.dto.LoginDTO;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    public HttpEntity<?> loginOAuth2(@AuthenticationPrincipal OAuth2User user) {
        return ResponseEntity.ok(user.getAttributes());
    }

    public HttpEntity<?> login(LoginDTO loginDTO) {
        return ResponseEntity.ok(loginDTO);
    }
}