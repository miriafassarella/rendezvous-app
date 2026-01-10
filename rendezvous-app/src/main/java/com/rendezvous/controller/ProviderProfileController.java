package com.rendezvous.controller;

import com.rendezvous.domain.model.ProviderProfile;
import com.rendezvous.domain.repository.ProviderProfileRepositoy;
import com.rendezvous.domain.repository.UserRepository;
import com.rendezvous.domain.service.AccountService;
import com.rendezvous.dto.ProviderProfileDto.ProviderProfileRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/providers")
public class ProviderProfileController {

    @Autowired
    private ProviderProfileRepositoy repositoy;

    @Autowired
    private AccountService accountService;

    @PostMapping
    public ResponseEntity<ProviderProfile> createProvider(@RequestBody ProviderProfileRequestDTO providerDTO){
        ProviderProfile providerSave = accountService.createProvide(providerDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(providerSave);
    }
}
