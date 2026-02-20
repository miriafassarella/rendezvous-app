package com.rendezvous.controller;

import com.rendezvous.domain.model.ClientProfile;
import com.rendezvous.domain.service.ClientProfileService;
import com.rendezvous.dto.clientProfileDto.ClientProfileRequestDTO;
import com.rendezvous.dto.clientProfileDto.ClientProfileResponseDTO;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clients")
public class ClientProfileController {

    private ClientProfileService clientProfileService;

    public ClientProfileController(ClientProfileService clientProfileService){
        this.clientProfileService = clientProfileService;
    }

    @GetMapping
    public List<ClientProfileResponseDTO> findClientAll(){
        return clientProfileService.findClientAll();
    }

    @PostMapping
    public ResponseEntity<ClientProfileResponseDTO> createClient(@Valid @RequestBody ClientProfileRequestDTO clientDTO){
        ClientProfileResponseDTO newClient = clientProfileService.createClient(clientDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(newClient);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ClientProfile> delete(@PathVariable Long id){
        clientProfileService.deleteClient(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
