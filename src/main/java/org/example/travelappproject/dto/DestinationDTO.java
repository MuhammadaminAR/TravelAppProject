package org.example.travelappproject.dto;

import lombok.Builder;
import lombok.Value;
import org.example.travelappproject.entity.Attachment;

import java.util.List;

@Value
@Builder
public class DestinationDTO {  // bu dto filterlarni ya'ni destinationlar uchun ishlatiladi
    int destinationId;
    String destinationName;
    String destinationDescription;
    List<Attachment> attachments;

}
