package org.example.travelappproject.dto;

import lombok.Builder;
import lombok.Value;
import org.example.travelappproject.entity.Attachment;

import java.util.List;

@Value
@Builder
public class DestinationDTO {
    int destinationId;
    String destinationName;
    String destinationDescription;
    List<Attachment> attachments;

}
