package org.example.travelappproject.dto;

import lombok.Data;

@Data
public class MessageRequestDTO {
    private String text;
    private Integer toUserId;
    private Integer attachmentId;
    private String audioUrl;
}
