package com.rendezvous.controller;

import com.rendezvous.domain.model.TypeOfService;
import com.rendezvous.domain.repository.ServiceRepository;
import com.rendezvous.domain.service.ServiceService;
import com.rendezvous.dto.ServiceDto.ServiceRequestDTO;
import com.rendezvous.dto.ServiceDto.ServiseResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/services")
public class ServiceController {

    @Autowired
    private ServiceRepository serviceRepository;

    @Autowired
    private ServiceService serviceService;


    @PostMapping
    public ResponseEntity<ServiseResponseDTO> createService(@RequestBody ServiceRequestDTO serviceDTO){
         ServiseResponseDTO serviceSave = serviceService.createService(serviceDTO);
         return ResponseEntity.status(HttpStatus.CREATED).body(serviceSave);
    }
}
