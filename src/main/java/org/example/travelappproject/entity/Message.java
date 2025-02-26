package org.example.travelappproject.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "messages")
public class Message{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String text;
    @ManyToOne
    private Attachment file;
    @ManyToOne
    private User fromUser;

    private String audioURL;

    @ManyToOne
    private User toUser;
    @CreationTimestamp
    private LocalDateTime dateTime;
    @Column(columnDefinition = "boolean default false")

    public String getDateTime() {
        return dateTime.format(
                DateTimeFormatter.ofPattern("HH:mm")
        );
    }
}
