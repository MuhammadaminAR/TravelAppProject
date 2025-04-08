package org.example.travelappproject.repo;

import org.example.travelappproject.entity.City;
import org.example.travelappproject.entity.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CountryRepository extends JpaRepository<Country, Integer> {

    @Query("SELECT c FROM Country c WHERE LOWER(c.name) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<Country> searchByNameContainingIgnoreCase(String keyword);
}