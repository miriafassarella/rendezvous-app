package com.rendezvous.controller;

import com.rendezvous.domain.model.ProviderService;
import com.rendezvous.domain.service.ProviderServiceService;
import com.rendezvous.dto.providerServiceDto.ProviderServiceRequestDTO;
import com.rendezvous.dto.providerServiceDto.ProviderServiceResponseDTO;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("services")
public class ProviderServiceController {

    private ProviderServiceService providerServiceService;

    public ProviderServiceController(ProviderServiceService providerServiceService){
        this.providerServiceService = providerServiceService;
    }


    @GetMapping("/{providerId}")
    public List<ProviderServiceResponseDTO> findServicesAllByProvider(@PathVariable Long providerId){
        return providerServiceService.findServicesAllByProvider(providerId);
    }

    @PostMapping()
    public ResponseEntity<ProviderServiceResponseDTO> createService(@Valid @RequestBody ProviderServiceRequestDTO serviceDTO){
        ProviderServiceResponseDTO serviceSave = providerServiceService.createService(serviceDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(serviceSave);
    }

    @PutMapping("{providerServiceId}")
    public ResponseEntity<ProviderServiceResponseDTO> modifyProviderService(@RequestBody ProviderServiceRequestDTO providerServiceDTO, @PathVariable Long providerServiceId){
        ProviderServiceResponseDTO providerServiceResponseDTO = providerServiceService.modifyProviderService(providerServiceDTO, providerServiceId);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(providerServiceResponseDTO);

        //TODO não posso modificar um servico se existe um rendez-vous pra ele.
    }

    @DeleteMapping("{providerServiceId}")
    public ResponseEntity<ProviderService> deleteProviderService(@PathVariable Long providerServiceId){
        providerServiceService.deleteProviderService(providerServiceId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        //TODO
        // não posso deletar um serviço se existe um rendez-vous para ele.
    }
}
