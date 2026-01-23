package com.rendezvous.domain.service;

import com.rendezvous.domain.model.ProviderProfile;
import com.rendezvous.domain.model.ProviderService;
import com.rendezvous.domain.repository.ProviderProfileRepositoy;
import com.rendezvous.domain.repository.ServiceRepository;
import com.rendezvous.dto.ProviderServiceDto.ProviderServiceRequestDTO;
import com.rendezvous.dto.ProviderServiceDto.ProviderServiseResponseDTO;
import com.rendezvous.mapper.ProviderServiceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProviderServiceService {

    @Autowired
    private ProviderProfileRepositoy providerRepository;

    @Autowired
    private ProviderServiceMapper providerServiceMapper;

    @Autowired
    private ServiceRepository serviceRepository;

    @Autowired
    private ServiceRepository repository;

    public ProviderServiseResponseDTO createService(ProviderServiceRequestDTO serviceDTO){

        Optional<ProviderProfile> provider = providerRepository.findById(serviceDTO.getProviderId());

        ProviderService service = providerServiceMapper.toEntity(serviceDTO, provider.get());
        //TODO
        /*validar se id provider existe*/
        ProviderService serviceSaved = serviceRepository.save(service);

        return providerServiceMapper.toResponseDTO(serviceSaved);
    }
}
