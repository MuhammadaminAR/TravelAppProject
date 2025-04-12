package org.example.travelappproject.controller;

import jakarta.servlet.annotation.MultipartConfig;
import lombok.RequiredArgsConstructor;
import org.example.travelappproject.dto.ProfileDTO;
import org.example.travelappproject.entity.Attachment;
import org.example.travelappproject.entity.AttachmentContent;
import org.example.travelappproject.entity.User;
import org.example.travelappproject.repo.AttachmentContentRepository;
import org.example.travelappproject.repo.AttachmentRepository;
import org.example.travelappproject.service.UserService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
@MultipartConfig
public class UserController {

    private final UserService userService;
    private final AttachmentRepository attachmentRepository;
    private final AttachmentContentRepository attachmentContentRepository;

    @PostMapping("/new-password")
    public ResponseEntity<?> newPassword(
            @RequestParam String password,
            Principal principal
    ) {
        userService.resetPassword(password, principal);
        return ResponseEntity.ok(200);
    }

    @PostMapping("/profile/logout")
    public ResponseEntity<?> logout(){
        return ResponseEntity.ok("You have been logged out");
    }
    @PostMapping(value="/profile/edit",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> accountSecurity(ProfileDTO profileDTO, Principal principal,@RequestParam MultipartFile photo) throws IOException {
        Attachment attachment = Attachment.builder().
                filename(photo.getOriginalFilename()).
                build();
        attachmentRepository.save(attachment);
        AttachmentContent attachmentContent= AttachmentContent.builder()
                .content(photo.getBytes())
                .attachment(attachment)
                .build();
        attachmentContentRepository.save(attachmentContent);
        return userService.editProfile(profileDTO, principal,attachment);
    }

}
