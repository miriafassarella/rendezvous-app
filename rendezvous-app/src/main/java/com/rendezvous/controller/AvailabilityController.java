package com.rendezvous.controller;

import com.rendezvous.domain.model.Availability;
import com.rendezvous.domain.repository.AvailabilityRepository;
import com.rendezvous.domain.service.AvailabilityService;
import com.rendezvous.dto.AvailabilityDto.AvailabilityRequestDTO;
import com.rendezvous.dto.AvailabilityDto.AvailabilityResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/availabilities")
public class AvailabilityController {

    @Autowired
    private AvailabilityService availabilityService;

    @Autowired
    private AvailabilityRepository availabilityRepository;

    @PostMapping
    public ResponseEntity<AvailabilityResponseDTO> createAvailability(@RequestBody AvailabilityRequestDTO availabilityDTO){
       AvailabilityResponseDTO newAvailability = availabilityService.createAvailability(availabilityDTO);
       return ResponseEntity.status(HttpStatus.CREATED).body(newAvailability);
   }
}
