package org.example.travelappproject.dto;

import lombok.Setter;
import lombok.Value;
import org.springframework.web.bind.annotation.GetMapping;


@Value
public class MessageDTO {
   String message;
   Integer toUserId;
   Integer fromUserId;
   Integer attachmentId;
}
