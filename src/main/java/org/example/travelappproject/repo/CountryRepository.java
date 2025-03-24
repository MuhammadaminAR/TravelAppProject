package org.example.travelappproject.repo;

import org.example.travelappproject.entity.Country;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryRepository extends JpaRepository<Country, Integer> {
}