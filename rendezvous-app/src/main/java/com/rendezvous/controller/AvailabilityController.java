package com.rendezvous.controller;

import com.rendezvous.domain.service.AvailabilityService;
import com.rendezvous.dto.availabilityDto.AvailabilityRequestDTO;
import com.rendezvous.dto.availabilityDto.AvailabilityResponseDTO;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("availabilities")
public class AvailabilityController {

    private AvailabilityService availabilityService;

    public AvailabilityController(AvailabilityService availabilityService){
        this.availabilityService = availabilityService;
    }

    @GetMapping
    public ResponseEntity<List<AvailabilityResponseDTO>> findAvailabilitiesAll(){
        List<AvailabilityResponseDTO> availabilities = availabilityService.findAvailabilityAll();
        return ResponseEntity.status(HttpStatus.OK).body(availabilities);
    }

    @PostMapping()
    public ResponseEntity<AvailabilityResponseDTO> createAvailability(@Valid @RequestBody AvailabilityRequestDTO availabilityDTO){
        AvailabilityResponseDTO newAvailability = availabilityService.createAvailability(availabilityDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(newAvailability);
    }

    @PutMapping("/{availabilityId}")
    public ResponseEntity<AvailabilityResponseDTO> modifyAvailability(@RequestBody AvailabilityRequestDTO availabilityRequestDTO, @PathVariable Long availabilityId){
        AvailabilityResponseDTO availabilityResponseDTO = availabilityService.modifyAvailability(availabilityRequestDTO, availabilityId);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(availabilityResponseDTO);
        //TODO também não devo poder modificar uma disponibilidade se existe um rendez-vous nesta disponibilidade.
    }

    @DeleteMapping("/{availabilityId}")
    public ResponseEntity<AvailabilityResponseDTO> deleteAvailability(@PathVariable Long availabilityId){
        availabilityService.deleteAvailability(availabilityId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

    }

    @GetMapping("/{providerId}")
    public ResponseEntity<List<AvailabilityResponseDTO>> findByProviderId(@PathVariable Long providerId){
        List<AvailabilityResponseDTO> avaibilitiesDto = availabilityService.findByProviderId(providerId);
        return ResponseEntity.status(HttpStatus.OK).body(avaibilitiesDto);
    }
}
