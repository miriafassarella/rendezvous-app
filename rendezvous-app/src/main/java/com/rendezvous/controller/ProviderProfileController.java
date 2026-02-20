package com.rendezvous.controller;

import com.rendezvous.domain.model.Availability;
import com.rendezvous.domain.model.ProviderProfile;
import com.rendezvous.domain.service.AvailabilityService;
import com.rendezvous.domain.service.ProviderProfileService;
import com.rendezvous.domain.service.ProviderServiceService;
import com.rendezvous.dto.availabilityDto.AvailabilityRequestDTO;
import com.rendezvous.dto.availabilityDto.AvailabilityResponseDTO;
import com.rendezvous.dto.providerProfileDto.ProviderProfileRequestDTO;
import com.rendezvous.dto.providerProfileDto.ProviderProfileResponseDTO;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/providers")
public class ProviderProfileController {

    private ProviderProfileService providerProfileService;


    public ProviderProfileController(ProviderProfileService providerProfileService){

        this.providerProfileService = providerProfileService;
    }

    @GetMapping
    public List<ProviderProfileResponseDTO> findProviderAll(){
        return providerProfileService.findProviderAll();
    }

    @PostMapping
    public ResponseEntity<ProviderProfileResponseDTO> createProvider(@Valid @RequestBody ProviderProfileRequestDTO providerDTO){
        ProviderProfileResponseDTO providerSave = providerProfileService.createProvide(providerDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(providerSave);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ProviderProfile> deleteProvider(@PathVariable Long id){
        providerProfileService.deleteProvider(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
