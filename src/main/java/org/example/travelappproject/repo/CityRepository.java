package org.example.travelappproject.repo;

import org.example.travelappproject.entity.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CityRepository extends JpaRepository<City, Integer> {
    List<City> findByNameLikeIgnoreCase(String name);


    @Query("SELECT c FROM City c WHERE LOWER(c.name) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<City> searchByNameContainingIgnoreCase(String keyword);
}