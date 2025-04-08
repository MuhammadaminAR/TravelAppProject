package org.example.travelappproject.config;

import org.example.travelappproject.entity.Role;
import org.example.travelappproject.entity.User;
import org.example.travelappproject.enums.RoleName;
import org.example.travelappproject.repo.RoleRepository;
import org.example.travelappproject.repo.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataLoader implements CommandLineRunner {
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    public DataLoader(RoleRepository roleRepository, PasswordEncoder passwordEncoder, UserRepository userRepository) {
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        List<Role> allRoles = roleRepository.findAll();
        if (allRoles.isEmpty()) {
            Role role = new Role();
            role.setRoleName(RoleName.ROLE_USER);
            roleRepository.save(role);
            Role role2 = new Role();
            role2.setRoleName(RoleName.ROLE_ADMIN);
            roleRepository.save(role2);
            Role role3 = new Role();
            role3.setRoleName(RoleName.ROLE_SUPER_ADMIN);
            roleRepository.save(role3);
            Role role4 = new Role();
            role4.setRoleName(RoleName.ROLE_OPERATOR);
            roleRepository.save(role4);
        }
        if(userRepository.findAll().isEmpty()){
            for (Role role : allRoles) {
                if (role.getRoleName().equals(RoleName.ROLE_SUPER_ADMIN)) {
                    User user1 = User.builder()
                            .roles(new ArrayList<>(List.of(role)))
                            .email("superadmin@123gmail.com")
                            .password(passwordEncoder.encode("root123"))
                            .build();
                    userRepository.save(user1);
                }
                if (role.getRoleName().equals(RoleName.ROLE_OPERATOR)) {
                    User user1 = User.builder()
                            .roles(new ArrayList<>(List.of(role)))
                            .email("abdullayevmuhammadamin89@gmail.com")
                            .password(passwordEncoder.encode("root123"))
                            .build();
                    userRepository.save(user1);
                }
                if (role.getRoleName().equals(RoleName.ROLE_USER)) {
                    User user1 = User.builder()
                            .roles(new ArrayList<>(List.of(role)))
                            .email("kinouchunok777@gmail.com")
                            .password(passwordEncoder.encode("root123"))
                            .build();
                    userRepository.save(user1);
                }
            }
        }
    }
}
