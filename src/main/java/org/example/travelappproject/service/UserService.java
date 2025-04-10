package org.example.travelappproject.service;

import org.example.travelappproject.dto.GoogleUserDTO;
import org.example.travelappproject.dto.LoginDTO;
import org.example.travelappproject.dto.UserCreateDTO;
import org.springframework.http.ResponseEntity;

import java.security.Principal;

public interface UserService {

    ResponseEntity<?> registerUser(UserCreateDTO userCreateDTO);

    ResponseEntity<?> loginUser(LoginDTO loginDTO);

    ResponseEntity<?> resetPassword(String password, Principal user);

    ResponseEntity<?> registerWithGoogle(String googleId, GoogleUserDTO userCreateDTO);

    ResponseEntity<?> loginWithGoogle(String googleId);
}
