package com.rendezvous.controller;

import com.rendezvous.domain.model.ProviderProfile;
import com.rendezvous.domain.repository.ProviderProfileRepositoy;
import com.rendezvous.domain.service.ClientProfileService;
import com.rendezvous.domain.service.ProviderProfileService;
import com.rendezvous.dto.providerProfileDto.ProviderProfileRequestDTO;
import com.rendezvous.dto.providerProfileDto.ProviderProfileResponseDTO;
import jakarta.validation.Valid;
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
    private ProviderProfileService providerProfileService;

    @GetMapping
    public List<ProviderProfileResponseDTO> findProviderAll(){
        return providerProfileService.findProviderAll();
    }

    @PostMapping
    public ResponseEntity<ProviderProfileResponseDTO> createProvider(@Valid @RequestBody ProviderProfileRequestDTO providerDTO){
        ProviderProfileResponseDTO providerSave = providerProfileService.createProvide(providerDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(providerSave);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ProviderProfile> delete(@PathVariable Long id){
        providerProfileService.deleteProvider(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
