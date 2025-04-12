package org.example.travelappproject.controller;

import lombok.RequiredArgsConstructor;
import org.example.travelappproject.entity.Hotel;
import org.example.travelappproject.entity.Room;
import org.example.travelappproject.repo.HotelRepository;
import org.example.travelappproject.repo.RoomRepository;
import org.example.travelappproject.service.BookingService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.Optional;

@RestController
@RequestMapping("/api/booking")
@RequiredArgsConstructor
public class BookingController {


    private final RoomRepository roomRepository;

    @PostMapping
    public ResponseEntity<?> booking(@RequestParam Integer roomId, @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate bookingDate) {
        Optional<Room> optionalRoom = roomRepository.findById(roomId);
        if (optionalRoom.isPresent()) {
            Room room = optionalRoom.get();
            if (!room.getIsAvailable()){
                room.setIsAvailable(true);
                room.setBusyRoom(bookingDate);
                roomRepository.save(room);
                return ResponseEntity.ok("Room Booked");
            }else{
                return ResponseEntity.ok("Room already booked");
            }
        }
        return ResponseEntity.ok("Room is Not Available");
    }
}
