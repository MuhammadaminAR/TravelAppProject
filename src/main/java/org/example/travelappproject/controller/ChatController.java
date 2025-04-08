package org.example.travelappproject.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.RequiredArgsConstructor;
import org.example.travelappproject.dto.MessageRequestDTO;
import org.example.travelappproject.entity.*;
import org.example.travelappproject.enums.RoleName;
import org.example.travelappproject.repo.*;
import org.example.travelappproject.service.MessageService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/chat")
@RequiredArgsConstructor
public class ChatController {

    private final MessageRepository messageRepository;
    private final MessageService messageService;
    private final UserRepository userRepository;

    @PostMapping(value = "/messages", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> sendMessage(
            @RequestPart(required = false) String text,
            @RequestPart(required = false) String audioUrl,
            @RequestPart(required = false) MultipartFile file,
            @AuthenticationPrincipal User currentUser
    ) throws IOException {
        Integer toUserId=null;
        List<User> users = userRepository.findAll();
        for (User user : users) {
            if (user.getRoles().contains((RoleName.ROLE_OPERATOR))) {
                toUserId = user.getId();
                break;
            }
        }
        if (toUserId == null) {
            return ResponseEntity.badRequest().body("No operator found");
        }
        System.out.println("userId: "+toUserId);
        Message message = messageService.saveMessage(text, toUserId, audioUrl, file, currentUser);
        return ResponseEntity.ok(Objects.requireNonNullElse(message, "null"));
    }

    @GetMapping("/history")
    public ResponseEntity<List<Message>> getChatHistory(
            @RequestParam Integer otherUserId,
            @AuthenticationPrincipal User currentUser) {

        List<Message> messages = messageRepository.findChatHistory(
                currentUser.getId(),
                otherUserId
        );

        return ResponseEntity.ok(messages);
    }

    @GetMapping("/conversations")
    public ResponseEntity<List<User>> getConversations(
            @AuthenticationPrincipal User currentUser) {

        List<User> conversationPartners = messageRepository
                .findConversationPartners(currentUser.getId());

        return ResponseEntity.ok(conversationPartners);
    }
}
