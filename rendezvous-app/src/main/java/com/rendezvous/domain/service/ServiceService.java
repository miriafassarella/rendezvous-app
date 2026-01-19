package com.rendezvous.domain.service;

import com.rendezvous.domain.model.ProviderProfile;
import com.rendezvous.domain.model.TypeOfService;
import com.rendezvous.domain.repository.ProviderProfileRepositoy;
import com.rendezvous.domain.repository.ServiceRepository;
import com.rendezvous.dto.ServiceDto.ServiceRequestDTO;
import com.rendezvous.dto.ServiceDto.ServiseResponseDTO;
import com.rendezvous.mapper.ServiceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ServiceService {

    @Autowired
    private ProviderProfileRepositoy providerRepository;

    @Autowired
    private ServiceMapper serviceMapper;

    @Autowired
    private ServiceRepository serviceRepository;

    @Autowired
    private ServiceRepository repository;

    public ServiseResponseDTO createService(ServiceRequestDTO serviceDTO){

        Optional<ProviderProfile> provider = providerRepository.findById(serviceDTO.getProviderId());

        TypeOfService service = serviceMapper.toEntity(serviceDTO, provider.get());
        //TODO
        /*validar se id provider existe*/
        TypeOfService serviceSaved = serviceRepository.save(service);

        return serviceMapper.toResponseDTO(serviceSaved);
    }
}
