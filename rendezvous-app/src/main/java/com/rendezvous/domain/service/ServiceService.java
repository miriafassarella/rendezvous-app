package com.rendezvous.domain.service;

import com.rendezvous.domain.model.ProviderProfile;
import com.rendezvous.domain.model.TypeOfService;
import com.rendezvous.domain.repository.ProviderProfileRepositoy;
import com.rendezvous.domain.repository.ServiceRepository;
import com.rendezvous.dto.ServiceDto.ServiceRequestDTO;
import com.rendezvous.dto.ServiceDto.ServiseResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ServiceService {

    @Autowired
    private ProviderProfileRepositoy providerRepository;

    @Autowired
    private ServiceRepository repository;

    public ServiseResponseDTO createService(ServiceRequestDTO serviceDTO){

        TypeOfService service = new TypeOfService();

        service.setName(serviceDTO.getName());
        service.setDescription(serviceDTO.getDescription());
        service.setDuration_minutes(serviceDTO.getDurationMinutes());
        service.setBuffer_minutes(serviceDTO.getBufferMinutes());
        service.setPrice(serviceDTO.getPrice());
        service.setActive(serviceDTO.isActive());



        Optional<ProviderProfile> provider = providerRepository.findById(serviceDTO.getProviderId());
        service.setProvider(provider.get());


        TypeOfService saved = repository.save(service);

       return new ServiseResponseDTO(
               saved.getId(),
               saved.getName(),
               saved.getDescription(),
               saved.getDuration_minutes(),
               saved.getBuffer_minutes(),
               saved.getPrice(),
               saved.isActive()
       );


    }

}
