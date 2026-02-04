package com.rendezvous.domain.service;

import com.rendezvous.domain.model.ProviderProfile;
import com.rendezvous.domain.model.ProviderService;
import com.rendezvous.domain.repository.ProviderProfileRepositoy;
import com.rendezvous.domain.repository.ProviderServiceRepository;
import com.rendezvous.dto.providerServiceDto.ProviderServiceRequestDTO;
import com.rendezvous.dto.providerServiceDto.ProviderServiseResponseDTO;
import com.rendezvous.exception.EntityNotFoundException;
import com.rendezvous.mapper.ProviderServiceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ProviderServiceService {

    @Autowired
    private ProviderProfileRepositoy providerRepository;

    @Autowired
    private ProviderServiceMapper providerServiceMapper;

    @Autowired
    private ProviderServiceRepository providerServiceRepository;

    @Autowired
    private ProviderServiceRepository repository;

    @Transactional
    public ProviderServiseResponseDTO createService(ProviderServiceRequestDTO serviceDTO){

        Optional<ProviderProfile> provider = providerRepository.findById(serviceDTO.getProviderId());

        ProviderService service = providerServiceMapper.toEntity(serviceDTO, provider.get());
        //TODO
        /*validar se id provider existe*/
        ProviderService serviceSaved = providerServiceRepository.save(service);

        return providerServiceMapper.toResponseDTO(serviceSaved);
    }

    /**
     * A method that brings together all the services of a provider.
     * @param providerId is the provider ID
     * @return returns a list of all services from that provider.
     * @throws throws the exception EntityNotFoundException if the provider does not exist
     * */

    @Transactional
    public List<ProviderServiseResponseDTO> findServicesAllByProvider(Long providerId){
        ProviderProfile provider = providerRepository.findById(providerId)
                .orElseThrow(()-> new EntityNotFoundException("This provider id " + providerId + " does not exist."));

        List<ProviderService> services = repository.findByProviderId(providerId);
        return services.stream()
                .map(service -> providerServiceMapper.toResponseDTO(service))
                .toList();
    }
}
