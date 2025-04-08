package org.example.travelappproject.service.Impl;

import org.example.travelappproject.dto.DestinationDTO;
import org.example.travelappproject.entity.Continent;
import org.example.travelappproject.entity.Destination;
import org.example.travelappproject.repo.ContinentRepository;
import org.example.travelappproject.repo.DestinationRepository;
import org.example.travelappproject.service.DestinationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DestinationServiceImpl implements DestinationService {
    private final DestinationRepository destinationRepository;
    private final ContinentRepository continentRepository;

    public DestinationServiceImpl(DestinationRepository destinationRepository, ContinentRepository continentRepository) {
        this.destinationRepository = destinationRepository;
        this.continentRepository = continentRepository;
    }

    @Override
    public ResponseEntity<?> getDestinations(String continent) {
        List<Destination> destinationList = null;
        if (continent.equals("All")) {
            destinationList = destinationRepository.findAll();
        } else {
            Continent continentName = continentRepository.findByName(continent);
            destinationList = destinationRepository.findByCity_Country_Continent_Id(continentName.getId());
        }
        return getResponseEntity(destinationList);
    }   

    private ResponseEntity<?> getResponseEntity(List<Destination> byCityCountryContinentIdList) {
        List<DestinationDTO> destinationDTOList = new ArrayList<>();
        for (Destination destination : byCityCountryContinentIdList) {
            DestinationDTO destinationDTO = DestinationDTO.builder()
                    .destinationId(destination.getId())
                    .destinationName(destination.getName())
                    .destinationDescription(destination.getDescription())
                    .attachments(destination.getAttachmentList())
                    .build();
            destinationDTOList.add(destinationDTO);
        }
        return new ResponseEntity<>(destinationDTOList, HttpStatus.OK);
    }

}
