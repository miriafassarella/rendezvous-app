package com.rendezvous.controller;

import com.rendezvous.domain.model.ProviderProfile;
import com.rendezvous.domain.repository.ProviderProfileRepositoy;
import com.rendezvous.domain.service.AvailabilityService;
import com.rendezvous.domain.service.ClientProfileService;
import com.rendezvous.domain.service.ProviderProfileService;
import com.rendezvous.domain.service.ProviderServiceService;
import com.rendezvous.dto.availabilityDto.AvailabilityRequestDTO;
import com.rendezvous.dto.availabilityDto.AvailabilityResponseDTO;
import com.rendezvous.dto.providerProfileDto.ProviderProfileRequestDTO;
import com.rendezvous.dto.providerProfileDto.ProviderProfileResponseDTO;
import com.rendezvous.dto.providerServiceDto.ProviderServiceRequestDTO;
import com.rendezvous.dto.providerServiceDto.ProviderServiseResponseDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/providers")
public class ProviderProfileController {

    private AvailabilityService availabilityService;

    private ProviderProfileService providerProfileService;

    private ProviderServiceService providerServiceService;

    public ProviderProfileController(ProviderProfileService providerProfileService, ProviderServiceService providerServiceService,
    AvailabilityService availabilityService){

        this.providerProfileService = providerProfileService;
        this.providerServiceService = providerServiceService;
        this.availabilityService = availabilityService;
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
    public ResponseEntity<ProviderProfile> delete(@PathVariable Long id){
        providerProfileService.deleteProvider(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("/{providerId}/services")
    public List<ProviderServiseResponseDTO> findServicesAllByProvider(@PathVariable Long providerId){
        return providerServiceService.findServicesAllByProvider(providerId);
    }

    @PostMapping("/service")
    public ResponseEntity<ProviderServiseResponseDTO> createService(@Valid @RequestBody ProviderServiceRequestDTO serviceDTO){
        ProviderServiseResponseDTO serviceSave = providerServiceService.createService(serviceDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(serviceSave);
    }

    @PostMapping("/availability")
    public ResponseEntity<AvailabilityResponseDTO> createAvailability(@Valid @RequestBody AvailabilityRequestDTO availabilityDTO){
        AvailabilityResponseDTO newAvailability = availabilityService.createAvailability(availabilityDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(newAvailability);
    }

}
