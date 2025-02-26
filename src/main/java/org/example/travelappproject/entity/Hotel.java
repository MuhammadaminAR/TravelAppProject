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
    private String address;
    private String description;
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Amenity> amenities;
    @OneToMany(fetch = FetchType.LAZY)
    private List<Attachment> photos;
}
