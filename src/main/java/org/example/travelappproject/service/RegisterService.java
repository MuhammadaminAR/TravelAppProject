package org.example.travelappproject.service;


import org.example.travelappproject.dto.RegisterDTO;
import org.example.travelappproject.entity.Role;
import org.example.travelappproject.entity.User;
import org.example.travelappproject.repo.RoleRepository;
import org.example.travelappproject.repo.UserRepository;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RegisterService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public RegisterService(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public HttpEntity<?> registerOAuth2(OAuth2User oAuth2User, String password) {
        List<Role> allRoles = roleRepository.findAll();
        User user = User.builder()
                .roles(new ArrayList<>(List.of(allRoles.get(0))))
                .email(oAuth2User.getAttribute("email"))
                .password(passwordEncoder.encode(password))
                .fullName(oAuth2User.getAttribute("name"))
                .build();
        userRepository.save(user);
        return ResponseEntity.ok("Registered successfully");
    }

    public HttpEntity<?> register(RegisterDTO registerDTO) {
        return ResponseEntity.ok(registerDTO);
    }
}
