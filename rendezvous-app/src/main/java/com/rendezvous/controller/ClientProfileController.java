package com.rendezvous.controller;

import com.rendezvous.domain.model.ClientProfile;
import com.rendezvous.domain.service.ClientProfileService;
import com.rendezvous.dto.ClientProfileDto.ClientProfileRequestDTO;
import com.rendezvous.dto.ClientProfileDto.ClientProfileResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clients")
public class ClientProfileController {

    @Autowired
    ClientProfileService clientProfileService;

    @GetMapping
    public List<ClientProfileResponseDTO> findAll(){
        return clientProfileService.findAll();
    }

    @PostMapping
    public ResponseEntity<ClientProfile> createClient(@RequestBody ClientProfileRequestDTO clientDTO){
        ClientProfile newClient = clientProfileService.createClient(clientDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(newClient);
    }



}
