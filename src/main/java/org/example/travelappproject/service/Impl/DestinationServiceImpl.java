package org.example.travelappproject.service.Impl;

import org.example.travelappproject.dto.DestinationDTO;
import org.example.travelappproject.dto.ForSearchHotelDto;
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
import java.util.Optional;

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

    @Override
    public ResponseEntity<?> getDestinationName(int destinationId) {
        Optional<Destination> destinationOptional = destinationRepository.findById(destinationId);
        if (destinationOptional.isPresent()) {
            Destination destination = destinationOptional.get();
            ForSearchHotelDto forSearchHotelDto = new ForSearchHotelDto();
            forSearchHotelDto.setDestinationName(destination.getName());
            forSearchHotelDto.setAttachment(destination.getAttachmentList().get(0));
            return new ResponseEntity<>(forSearchHotelDto, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
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
        return new ResponseEntity<>(destinationDTOList, HttpStatus.OK); // bu destination qaytaradi
    }

}
