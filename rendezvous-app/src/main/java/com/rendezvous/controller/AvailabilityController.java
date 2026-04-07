package com.rendezvous.controller;

import com.rendezvous.domain.model.User;
import com.rendezvous.domain.service.AvailabilityService;
import com.rendezvous.dto.availabilityDto.AvailabilityRequestDTO;
import com.rendezvous.dto.availabilityDto.AvailabilityResponseDTO;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
    @PreAuthorize("hasAuthority('SEARCH_AVAIBILITY')")
    public ResponseEntity<List<AvailabilityResponseDTO>> findAvailabilitiesAll(){
        List<AvailabilityResponseDTO> availabilities = availabilityService.findAvailabilityAll();
        return ResponseEntity.status(HttpStatus.OK).body(availabilities);
    }

    @PostMapping()
    @PreAuthorize("hasAuthority('CREATE_AVAIBILITY')")
    public ResponseEntity<AvailabilityResponseDTO> createAvailability(@Valid @RequestBody AvailabilityRequestDTO availabilityDTO){
        AvailabilityResponseDTO newAvailability = availabilityService.createAvailability(availabilityDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(newAvailability);
    }

    @PutMapping("/{availabilityId}")
    @PreAuthorize("hasAuthority('UPDATE_AVAIBILITY')")
    public ResponseEntity<AvailabilityResponseDTO> modifyAvailability(@RequestBody AvailabilityRequestDTO availabilityRequestDTO, @PathVariable Long availabilityId){
        AvailabilityResponseDTO availabilityResponseDTO = availabilityService.modifyAvailability(availabilityRequestDTO, availabilityId);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(availabilityResponseDTO);

    }

    @DeleteMapping("/{availabilityId}")
    @PreAuthorize("hasAuthority('DELETE_AVAIBILITY')")
    public ResponseEntity<AvailabilityResponseDTO> deleteAvailability(@PathVariable Long availabilityId){
        availabilityService.deleteAvailability(availabilityId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

    }

    @GetMapping("/{providerId}")
    @PreAuthorize("hasAuthority('SEARCH_OWN_AVAIBILITY')")
    public ResponseEntity<List<AvailabilityResponseDTO>> findByProviderId(@PathVariable Long providerId, @AuthenticationPrincipal User loggedUser){
        List<AvailabilityResponseDTO> avaibilitiesDto = availabilityService.findByProviderId(providerId, loggedUser);
        return ResponseEntity.status(HttpStatus.OK).body(avaibilitiesDto);
    }
}
