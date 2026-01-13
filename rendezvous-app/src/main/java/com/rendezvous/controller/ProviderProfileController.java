package com.rendezvous.controller;

import com.rendezvous.domain.model.ClientProfile;
import com.rendezvous.domain.model.ProviderProfile;
import com.rendezvous.domain.repository.ProviderProfileRepositoy;
import com.rendezvous.domain.repository.UserRepository;
import com.rendezvous.domain.service.AccountService;
import com.rendezvous.dto.ProviderProfileDto.ProviderProfileRequestDTO;
import com.rendezvous.dto.ProviderProfileDto.ProviderProfileResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/providers")
public class ProviderProfileController {

    @Autowired
    private ProviderProfileRepositoy repositoy;

    @Autowired
    private AccountService accountService;

    @PostMapping
    public ResponseEntity<ProviderProfileResponseDTO> createProvider(@RequestBody ProviderProfileRequestDTO providerDTO){
        ProviderProfileResponseDTO providerSave = accountService.createProvide(providerDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(providerSave);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ProviderProfile> delete(@PathVariable Long id){
        accountService.deleteProvider(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
