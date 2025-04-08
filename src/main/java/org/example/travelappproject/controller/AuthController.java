package org.example.travelappproject.controller;

import lombok.RequiredArgsConstructor;
import org.example.travelappproject.dto.LoginDTO;
import org.example.travelappproject.dto.UserCreateDTO;
import org.example.travelappproject.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;

    @PostMapping("/sign-up")
    public ResponseEntity<?> signUp(@RequestBody UserCreateDTO userCreateDTO){
        userService.registerUser(userCreateDTO);
        return ResponseEntity.ok().body(200);
    }
    @PostMapping("/sign-in")
    public ResponseEntity<?> signIn(@RequestBody LoginDTO loginDTO){
        return userService.loginUser(loginDTO);
    }

}
