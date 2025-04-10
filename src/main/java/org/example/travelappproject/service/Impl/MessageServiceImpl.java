package org.example.travelappproject.service.Impl;

import lombok.RequiredArgsConstructor;
import org.example.travelappproject.entity.Attachment;
import org.example.travelappproject.entity.AttachmentContent;
import org.example.travelappproject.entity.Message;
import org.example.travelappproject.entity.User;
import org.example.travelappproject.repo.AttachmentContentRepository;
import org.example.travelappproject.repo.AttachmentRepository;
import org.example.travelappproject.repo.MessageRepository;
import org.example.travelappproject.repo.UserRepository;
import org.example.travelappproject.service.MessageService;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService {

    private final MessageRepository messageRepository;
    private final UserRepository userRepository;
    private final AttachmentRepository attachmentRepository;
    private final AttachmentContentRepository attachmentContentRepository;

    @Override
    public Message saveMessage(String text, Integer toUserId, String audioUrl, MultipartFile file, Principal principal) throws IOException {
        User currentUser = userRepository.findByEmail(principal.getName());
        Message message = new Message();
        message.setText(text);
        message.setAudioUrl(audioUrl);
        message.setFromUser(currentUser);

        // Set recipient
        User toUser = userRepository.findById(toUserId)
                .orElseThrow(() -> new RuntimeException("Recipient not found"));
        message.setToUser(toUser);

        // Save attachment if exists
        if (file != null && !file.isEmpty()) {
            Attachment attachment = Attachment.builder().
                    filename(file.getOriginalFilename()).
                    build();
            attachmentRepository.save(attachment);
            AttachmentContent attachmentContent= AttachmentContent.builder()
                    .content(file.getBytes())
                    .attachment(attachment)
                    .build();
            attachmentContentRepository.save(attachmentContent);
            message.setFile(attachment);
        }

        message.setDateTime(LocalDateTime.now());
      return messageRepository.save(message);
    }
}
