package org.example.travelappproject.repo;

import org.example.travelappproject.entity.Message;
import org.example.travelappproject.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Integer> {

    @Query("SELECT m FROM Message m WHERE " +
            "(m.fromUser.id = :userId AND m.toUser.id = :otherUserId) OR " +
            "(m.fromUser.id = :otherUserId AND m.toUser.id = :userId) " +
            "ORDER BY m.dateTime ASC")
    List<Message> findChatHistory(@Param("userId") Integer userId,
                                  @Param("otherUserId") Integer otherUserId);


    @Query("SELECT DISTINCT u FROM User u " +
            "WHERE u.id IN (" +
            "SELECT m.toUser.id FROM Message m WHERE m.fromUser.id = :userId " +
            "UNION " +
            "SELECT m.fromUser.id FROM Message m WHERE m.toUser.id = :userId)")
    List<User> findConversationPartners(@Param("userId") Integer userId);
}