package org.example.travelappproject.service.util;

import org.example.travelappproject.dto.DestinationDTO;
import org.example.travelappproject.entity.City;
import org.example.travelappproject.entity.Continent;
import org.example.travelappproject.entity.Country;
import org.example.travelappproject.entity.Destination;
import org.example.travelappproject.repo.CityRepository;
import org.example.travelappproject.repo.ContinentRepository;
import org.example.travelappproject.repo.CountryRepository;
import org.example.travelappproject.repo.DestinationRepository;
import org.example.travelappproject.service.FilterUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FilterUtilService implements FilterUtil {

    private final CityRepository cityRepository;
    private final DestinationRepository destinationRepository;
    private final CountryRepository countryRepository;
    private final ContinentRepository continentRepository;

    public FilterUtilService(CityRepository cityRepository, DestinationRepository destinationRepository, CountryRepository countryRepository, ContinentRepository continentRepository) {
        this.cityRepository = cityRepository;
        this.destinationRepository = destinationRepository;
        this.countryRepository = countryRepository;
        this.continentRepository = continentRepository;
    }

    @Override
    public ResponseEntity<?> getDestinationsBySearchKeyword(String keyword) {
        if (keyword.isEmpty()) {
            return new ResponseEntity<>("Filter is empty", HttpStatus.BAD_REQUEST);
        }

        List<Destination> destinations = destinationRepository.searchByNameContainingIgnoreCase(keyword);
        if (!destinations.isEmpty()) {
            return getResponseEntity(destinations);
        }

        List<City> cityList = cityRepository.searchByNameContainingIgnoreCase(keyword);
        if (!cityList.isEmpty()) {
            return getDestinationsByCity(cityList);
        }

        List<Country> countries = countryRepository.searchByNameContainingIgnoreCase(keyword);
        if (!countries.isEmpty()) {
            return getDestinationsByCountriesName(countries);
        }

        List<Continent> continentList = continentRepository.searchByNameContainingIgnoreCase(keyword);
        if (!continentList.isEmpty()) {
            return getDestinationsByContinents(continentList);
        }
        return new ResponseEntity<>("Such a country does not exist", HttpStatus.BAD_REQUEST);
    }

    private ResponseEntity<?> getDestinationsByContinents(List<Continent> continentList) {
        List<Destination> destinationList = destinationRepository.findDestinationsByContinents(continentList);
        return getResponseEntity(destinationList);
    }

    private ResponseEntity<?> getDestinationsByCity(List<City> cityList) {
        List<Destination> destinations = destinationRepository.findDestinationsByCities(cityList);
        return getResponseEntity(destinations);
    }

    private ResponseEntity<?> getDestinationsByCountriesName(List<Country> countries) {
        List<Destination> destinations = destinationRepository.findDestinationsByCountries(countries);
        return getResponseEntity(destinations);
    }

    private ResponseEntity<?> getResponseEntity(List<Destination> destinations) {
        List<DestinationDTO> destinationDTOList = new ArrayList<>();
        for (Destination destination : destinations) {
            DestinationDTO destinationDTO = DestinationDTO.builder()
                    .destinationId(destination.getId())
                    .destinationName(destination.getName())
                    .destinationDescription(destination.getDescription())
                    .attachments(destination.getAttachmentList())
                    .build();
            destinationDTOList.add(destinationDTO);
        }
        return new ResponseEntity<>(destinationDTOList, HttpStatus.OK); // filter uchun destination
    }
}
