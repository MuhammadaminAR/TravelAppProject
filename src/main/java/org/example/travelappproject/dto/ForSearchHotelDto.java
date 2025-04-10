package org.example.travelappproject.dto;

//des
import lombok.Data;
import org.example.travelappproject.entity.Attachment;
@Data
public class ForSearchHotelDto {
    String destinationName;
    Attachment attachment;

}
