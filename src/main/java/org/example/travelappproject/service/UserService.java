package org.example.travelappproject.service;

import org.example.travelappproject.dto.LoginDTO;
import org.example.travelappproject.dto.UserCreateDTO;
import org.springframework.http.ResponseEntity;

public interface UserService {

    ResponseEntity<?> registerUser(UserCreateDTO userCreateDTO);

    ResponseEntity<?> loginUser(LoginDTO loginDTO);

    ResponseEntity<?> registerWithGoogle(UserCreateDTO userCreateDTO);
}
