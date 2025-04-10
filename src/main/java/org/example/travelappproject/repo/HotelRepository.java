package org.example.travelappproject.repo;

import org.example.travelappproject.entity.City;
import org.example.travelappproject.entity.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface HotelRepository extends JpaRepository<Hotel, Integer> {
    List<Hotel> findByCity(City city);

    @Query("""
        SELECT h FROM Hotel h
        WHERE LOWER(h.city.name) = LOWER(:cityName)
        AND (
            SELECT COUNT(r) FROM Room r
            WHERE r.hotel = h
            AND LOWER(r.roomStatus) = LOWER(:roomStatus)
            AND r.isAvailable = true
            AND (r.busyRoom IS NULL OR r.busyRoom NOT BETWEEN :checkInDate AND :checkOutDate)
            AND (
                (:guestsCount = 1 AND r.roomType = org.example.travelappproject.enums.RoomType.SINGLE)
                OR (:guestsCount = 2 AND r.roomType = org.example.travelappproject.enums.RoomType.DOUBLE)
                OR (:guestsCount > 2 AND r.roomType = org.example.travelappproject.enums.RoomType.FAMILY)
            )
        ) >= :roomsCount
    """)
    List<Hotel> searchAvailableHotels(
            @Param("cityName") String cityName,
            @Param("checkInDate") LocalDate checkInDate,
            @Param("checkOutDate") LocalDate checkOutDate,
            @Param("guestsCount") int guestsCount,
            @Param("roomsCount") int roomsCount,
            @Param("roomStatus") String roomStatus
    );
}