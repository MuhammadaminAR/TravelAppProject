package org.example.travelappproject.repo;

import org.example.travelappproject.entity.City;
import org.example.travelappproject.entity.Continent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ContinentRepository extends JpaRepository<Continent, Integer> {
    Continent findByName(String name);

    @Query("SELECT c FROM Continent c WHERE LOWER(c.name) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<Continent> searchByNameContainingIgnoreCase(String keyword); // bu
}