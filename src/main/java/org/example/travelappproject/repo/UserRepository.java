package org.example.travelappproject.repo;

import org.example.travelappproject.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByEmail(String email);
    boolean existsByGoogleId(String googleId);
    boolean existsByFacebookId(String facebookId);
    Optional<User> findByGoogleId(String googleId);

    Optional<User> findByFacebookId(String facebookId);
}