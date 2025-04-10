package org.example.travelappproject.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.travelappproject.enums.AccommodationType;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Hotel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;

    @Enumerated(EnumType.STRING)
    private AccommodationType accommodationType;

    @ManyToOne
    private City city;

    private String description;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Amenity> amenities;

    @OneToMany(fetch = FetchType.LAZY)
    private List<Attachment> photos;

    public Hotel(String name, AccommodationType accommodationType, City city, String description) {
        this.name = name;
        this.accommodationType = accommodationType;
        this.city = city;
        this.description = description; // bu h
    }
}
