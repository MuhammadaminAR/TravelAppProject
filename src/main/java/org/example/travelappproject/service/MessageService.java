package org.example.travelappproject.service;

import org.example.travelappproject.entity.Message;
import org.example.travelappproject.entity.User;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface MessageService {
    Message saveMessage(String text, Integer toUserId, String audioUrl, MultipartFile file, User currentUser) throws IOException;
}
