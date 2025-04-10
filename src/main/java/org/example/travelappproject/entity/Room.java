package org.example.travelappproject.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.travelappproject.enums.RoomStatus;
import org.example.travelappproject.enums.RoomType;

import java.time.LocalDate;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String roomNumber;

    @ManyToOne
    private Hotel hotel;

    @Enumerated(EnumType.STRING)
    private RoomStatus roomStatus;

    @Enumerated(EnumType.STRING)
    private RoomType roomType;

    private Double price;

    @Column(nullable = false)
    private Boolean isAvailable=false;

    private LocalDate busyRoom;

}
