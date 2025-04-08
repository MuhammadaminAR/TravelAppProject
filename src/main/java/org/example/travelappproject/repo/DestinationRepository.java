package org.example.travelappproject.repo;

import org.example.travelappproject.entity.City;
import org.example.travelappproject.entity.Continent;
import org.example.travelappproject.entity.Country;
import org.example.travelappproject.entity.Destination;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.List;

public interface DestinationRepository extends JpaRepository<Destination, Integer> {
    List<Destination> findByCity_Country_Continent_Id(Integer cityCountryContinentId);

    @Query("SELECT d FROM Destination d WHERE LOWER(d.name) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<Destination> searchByNameContainingIgnoreCase(String keyword);

    @Query("SELECT d FROM Destination d WHERE d.city IN :cityList")
    List<Destination> findDestinationsByCities(@Param("cityList") List<City> cityList);

    @Query("SELECT d FROM Destination d WHERE d.city.country IN :countries")
    List<Destination> findDestinationsByCountries(@Param("countries") List<Country> countries);

    @Query("SELECT d FROM Destination d WHERE d.city.country.continent IN :continents")
    List<Destination> findDestinationsByContinents(@Param("continents") List<Continent> continents);

}