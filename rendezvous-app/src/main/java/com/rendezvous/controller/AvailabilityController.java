package com.rendezvous.controller;

import com.rendezvous.domain.model.Availability;
import com.rendezvous.domain.repository.AvailabilityRepository;
import com.rendezvous.domain.service.AvailabilityService;
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

    @GetMapping
    public List<Availability> listAvailabilities(){
        return availabilityRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<Availability> addAvailability(@RequestBody Availability availability){
        Availability availabilitySave = availabilityService.addAvailability(availability);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Availability> updateAvailability(@PathVariable Long id, @RequestBody Availability availability){
        Availability availabilityModified = availabilityService.updateAvailability(id, availability);
        return ResponseEntity.status(HttpStatus.OK).body(availabilityModified);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Availability> removeAvailability(Long id){
        availabilityService.removeAvailability(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
