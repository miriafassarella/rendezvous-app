package com.rendezvous.controller;

import com.rendezvous.domain.model.ProviderService;
import com.rendezvous.domain.repository.ProviderServiceRepository;
import com.rendezvous.domain.service.ProviderServiceService;
import com.rendezvous.dto.providerServiceDto.ProviderServiceRequestDTO;
import com.rendezvous.dto.providerServiceDto.ProviderServiseResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/providers")
public class ProviderServiceController {

    @Autowired
    private ProviderServiceRepository providerServiceRepository;

    @Autowired
    private ProviderServiceService providerServiceService;

    @GetMapping("/{providerId}/services")
    public List<ProviderServiseResponseDTO> findServicesAllByProvider(@PathVariable Long providerId){
        return providerServiceService.findServicesAllByProvider(providerId);
    }

    @PostMapping("/service")
    public ResponseEntity<ProviderServiseResponseDTO> createService(@RequestBody ProviderServiceRequestDTO serviceDTO){
         ProviderServiseResponseDTO serviceSave = providerServiceService.createService(serviceDTO);
         return ResponseEntity.status(HttpStatus.CREATED).body(serviceSave);
    }


}

