package org.example.travelappproject.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.Value;
import org.springframework.web.bind.annotation.GetMapping;


@Getter
@Setter
public class MessageDTO {
   String message;
   Integer toUserId;
   Integer fromUserId;
   Integer attachmentId;
}
