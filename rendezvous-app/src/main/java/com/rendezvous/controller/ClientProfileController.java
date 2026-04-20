package com.rendezvous.controller;

import com.rendezvous.domain.model.ClientProfile;
import com.rendezvous.domain.service.ClientProfileService;
import com.rendezvous.dto.clientProfileDto.ClientProfileRequestDTO;
import com.rendezvous.dto.clientProfileDto.ClientProfileResponseDTO;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
    @PreAuthorize("hasAuthority('SEARCH_CLIENT_PROFILE')")
    public ResponseEntity<List<ClientProfileResponseDTO>> findClientAll(){
        List<ClientProfileResponseDTO> clients = clientProfileService.findClientAll();
        return ResponseEntity.status(HttpStatus.OK).body(clients);
    }

    @PreAuthorize("hasAuthority('SEARCH_CLIENT_PROFILE')")
    @GetMapping("/{clientId}")
    public ResponseEntity<ClientProfileResponseDTO> findById(@PathVariable Long clientId){
        ClientProfileResponseDTO client = clientProfileService.findById(clientId);
        return ResponseEntity.status(HttpStatus.OK).body(client);
    }

    @PostMapping()
    public ResponseEntity<ClientProfileResponseDTO> createClient(@Valid @RequestBody ClientProfileRequestDTO clientDTO){
        ClientProfileResponseDTO newClient = clientProfileService.createClient(clientDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(newClient);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('DELETE_CLIENT_PROFILE')")
    public ResponseEntity<?> delete(@PathVariable Long id){
        clientProfileService.deleteClient(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
