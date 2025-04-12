package org.example.travelappproject.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.RequiredArgsConstructor;
import org.example.travelappproject.dto.MessageDTO;
import org.example.travelappproject.dto.MessageRequestDTO;
import org.example.travelappproject.dto.UserDTO;
import org.example.travelappproject.entity.*;
import org.example.travelappproject.enums.RoleName;
import org.example.travelappproject.repo.*;
import org.example.travelappproject.service.MessageService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/api/chat")
@RequiredArgsConstructor
public class ChatController {

    private final MessageRepository messageRepository;
    private final MessageService messageService;
    private final UserRepository userRepository;

    @PreAuthorize("isAuthenticated()")
    @PostMapping(value = "/messages", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> sendMessage(
            @RequestPart(required = false) String text,
            @RequestPart(required = false) String audioUrl,
            @RequestPart(required = false) MultipartFile file,
            Principal principal
    ) throws IOException {
        Integer toUserId = null;
        List<User> users = userRepository.findAll();
        for (User user : users) {
            Optional<Role> toUserOptional = user.getRoles().stream()
                    .filter(r -> r.getRoleName().equals(RoleName.ROLE_OPERATOR))
                    .findFirst();
            if (toUserOptional.isPresent()) {
                toUserId = user.getId();
                break;
            }
        }
        if (toUserId == null) {
            return ResponseEntity.badRequest().body("No operator found");
        }
        System.out.println("userId: " + toUserId);
        Message message = messageService.saveMessage(text, toUserId, audioUrl, file, principal);

        if (message == null) {
            return ResponseEntity.badRequest().body("Something went wrong");
        }
        return ResponseEntity.ok("Message sent");
    }

    @GetMapping("/history")
    public ResponseEntity<List<?>> getChatHistory(
            @RequestParam Integer otherUserId,
            Principal principal) {
        User currentUser  = userRepository.findByEmail(principal.getName());
        List<Message> messages = messageRepository.findChatHistory(
                currentUser.getId(),
                otherUserId
        );
        List<MessageDTO> messageDTOS = new ArrayList<>();
        for (Message message : messages) {
            MessageDTO messageDTO= new MessageDTO();
            messageDTO.setMessage(message.getText());
            messageDTO.setFromUserId(message.getFromUser().getId());
            messageDTO.setToUserId(message.getToUser().getId());
            if (message.getFile()!=null){
                messageDTO.setAttachmentId(message.getFile().getId());
            }
            messageDTOS.add(messageDTO);
        }
        return ResponseEntity.ok(messageDTOS);
    }

    @GetMapping("/conversations")
    public ResponseEntity<?> getConversations(
            Principal principal) {

        User currentUser = userRepository.findByEmail(principal.getName());
        List<User> conversationPartners = messageRepository
                .findConversationPartners(currentUser.getId());

        List<UserDTO> userDTOS= new ArrayList<>();
        for (User conversationPartner : conversationPartners) {
            UserDTO userDTO = new UserDTO(
                    null,
                    conversationPartner.getEmail()
            );
            userDTOS.add(userDTO);
        }

        return ResponseEntity.ok(userDTOS);
    }
}
