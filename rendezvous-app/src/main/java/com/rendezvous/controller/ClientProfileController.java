package com.rendezvous.controller;

import com.rendezvous.domain.model.ClientProfile;
import com.rendezvous.domain.repository.ClientProfileRepository;
import com.rendezvous.domain.repository.UserRepository;
import com.rendezvous.domain.service.ClientProfileService;
import com.rendezvous.domain.service.UserService;
import com.rendezvous.dto.ClientProfileRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/clients")
public class ClientProfileController {

    @Autowired
    ClientProfileService clientProfileService;

    @PostMapping
    public ResponseEntity<ClientProfile> createClient(@RequestBody ClientProfileRequestDTO clientDTO){
        ClientProfile newClient = clientProfileService.createClient(clientDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(newClient);
    }



}
