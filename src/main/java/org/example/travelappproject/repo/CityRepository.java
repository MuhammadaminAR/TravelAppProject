package org.example.travelappproject.repo;

import org.example.travelappproject.entity.City;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CityRepository extends JpaRepository<City, Integer> {
}