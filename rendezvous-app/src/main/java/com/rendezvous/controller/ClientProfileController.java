package com.rendezvous.controller;

import com.rendezvous.domain.model.ClientProfile;
import com.rendezvous.domain.service.AccountService;
import com.rendezvous.domain.service.UserService;
import com.rendezvous.dto.clientProfileDto.ClientProfileRequestDTO;
import com.rendezvous.dto.clientProfileDto.ClientProfileResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clients")
public class ClientProfileController {

    @Autowired
    private UserService userService;

    @Autowired
    private AccountService accountService;

    @GetMapping
    public List<ClientProfileResponseDTO> findClientAll(){
        return accountService.findClientAll();
    }

    @PostMapping
    public ResponseEntity<ClientProfileResponseDTO> createClient(@RequestBody ClientProfileRequestDTO clientDTO){
        ClientProfileResponseDTO newClient = accountService.createClient(clientDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(newClient);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ClientProfile> delete(@PathVariable Long id){
        accountService.deleteClient(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
