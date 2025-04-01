package org.example.travelappproject.repo;

import org.example.travelappproject.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message, Integer> {
}