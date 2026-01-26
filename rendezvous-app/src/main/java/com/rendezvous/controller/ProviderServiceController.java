package com.rendezvous.controller;

import com.rendezvous.domain.repository.ProviderServiceRepository;
import com.rendezvous.domain.service.ProviderServiceService;
import com.rendezvous.dto.ProviderServiceDto.ProviderServiceRequestDTO;
import com.rendezvous.dto.ProviderServiceDto.ProviderServiseResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/services")
public class ProviderServiceController {

    @Autowired
    private ProviderServiceRepository providerServiceRepository;

    @Autowired
    private ProviderServiceService providerServiceService;


    @PostMapping
    public ResponseEntity<ProviderServiseResponseDTO> createService(@RequestBody ProviderServiceRequestDTO serviceDTO){
         ProviderServiseResponseDTO serviceSave = providerServiceService.createService(serviceDTO);
         return ResponseEntity.status(HttpStatus.CREATED).body(serviceSave);
    }
}

