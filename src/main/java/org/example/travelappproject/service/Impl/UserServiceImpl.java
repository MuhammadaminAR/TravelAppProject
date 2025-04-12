package org.example.travelappproject.service.Impl;

import org.example.travelappproject.dto.GoogleUserDTO;
import org.example.travelappproject.dto.LoginDTO;
import org.example.travelappproject.dto.ProfileDTO;
import org.example.travelappproject.dto.UserCreateDTO;
import org.example.travelappproject.entity.Attachment;
import org.example.travelappproject.entity.Role;
import org.example.travelappproject.entity.User;
import org.example.travelappproject.enums.RoleName;
import org.example.travelappproject.repo.RoleRepository;
import org.example.travelappproject.repo.UserRepository;
import org.example.travelappproject.service.JwtService;
import org.example.travelappproject.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, JwtService jwtService) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    @Override
    public ResponseEntity<?> registerUser(UserCreateDTO userCreateDTO) {
        String password = userCreateDTO.getPassword();
        String confirmPassword = userCreateDTO.getConfirmPassword();
        if (password.equals(confirmPassword)) {
            Role role = roleRepository.findByRoleName(RoleName.ROLE_USER);
            User user = new User();
            user.setEmail(userCreateDTO.getEmail());
            user.setPassword(passwordEncoder.encode(userCreateDTO.getPassword()));
            user.setOnline(false);
            user.setFullName(userCreateDTO.getFullName());
            user.setRoles(List.of(role));
            userRepository.save(user);
            return ResponseEntity.ok().body("User registered successfully");
        }
       return ResponseEntity.ok().body("Passwords do not match");
    }

    @Override
    public ResponseEntity<?> loginUser(LoginDTO loginDTO) {
            var authentication = new UsernamePasswordAuthenticationToken(loginDTO.getEmail(), loginDTO.getPassword());
            Authentication authenticate = authenticationManager.authenticate(authentication);
            User user = (User) authenticate.getPrincipal();
            String token = jwtService.generateToken(user);
        System.out.println(token);
        return ResponseEntity.ok().body(token);
    }

    @Override
    public ResponseEntity<?> resetPassword(String password, Principal principal) {
        User user = userRepository.findByEmail(principal.getName());
        if (user != null) {
            user.setPassword(passwordEncoder.encode(password));
            userRepository.save(user);
            return ResponseEntity.ok().body(user);
        }
        return ResponseEntity.ok().body("User doesn't exist");
    }

    @Override
    public ResponseEntity<?> registerWithGoogle(String googleId, GoogleUserDTO userCreateDTO) {
        if (userRepository.existsByGoogleId(googleId)) {
            throw new RuntimeException("Google user already exists with ID: " + googleId);
        }
        User user = new User();
        user.setGoogleId(googleId);
        user.setEmail(userCreateDTO.getEmail());
        user.setName(userCreateDTO.getName());
        user.setGivenName(userCreateDTO.getGivenName());
        user.setFamilyName(userCreateDTO.getFamilyName());
        user.setPassword(null);

        return ResponseEntity.ok(userRepository.save(user));
    }

    @Override
    public ResponseEntity<?> loginWithGoogle(String googleId) {
        User user = userRepository.findByGoogleId(googleId)
                .orElseThrow(() -> new RuntimeException("Google user not found with ID: " + googleId));
        return ResponseEntity.ok("Google sign-in successful for: " + user.getEmail());
    }

    @Override
    public ResponseEntity<?> editProfile(ProfileDTO profileDTO, Principal principal, Attachment attachment) {
        User user = userRepository.findByEmail(principal.getName());
        user.setFullName(profileDTO.getFullName());
        user.setAddress(profileDTO.getAddress());
        user.setLocation(profileDTO.getLocation());
        user.setPhone(profileDTO.getPhone());
        user.setPhoto(attachment);
        userRepository.save(user);
        return ResponseEntity.ok("Your your profile has been updated");
    }
}
