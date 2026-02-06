package com.rendezvous.controller;

import com.rendezvous.domain.repository.AvailabilityRepository;
import com.rendezvous.domain.service.AvailabilityService;
import com.rendezvous.dto.availabilityDto.AvailabilityRequestDTO;
import com.rendezvous.dto.availabilityDto.AvailabilityResponseDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/availabilities")
public class AvailabilityController {

    @Autowired
    private AvailabilityService availabilityService;

    @Autowired
    private AvailabilityRepository availabilityRepository;

    @PostMapping
    public ResponseEntity<AvailabilityResponseDTO> createAvailability(@Valid @RequestBody AvailabilityRequestDTO availabilityDTO){
       AvailabilityResponseDTO newAvailability = availabilityService.createAvailability(availabilityDTO);
       return ResponseEntity.status(HttpStatus.CREATED).body(newAvailability);
   }
}
