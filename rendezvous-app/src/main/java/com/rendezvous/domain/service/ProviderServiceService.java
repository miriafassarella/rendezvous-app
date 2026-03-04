package com.rendezvous.domain.service;

import com.rendezvous.domain.model.ProviderProfile;
import com.rendezvous.domain.model.ProviderService;
import com.rendezvous.domain.repository.ProviderProfileRepositoy;
import com.rendezvous.domain.repository.ProviderServiceRepository;
import com.rendezvous.dto.providerServiceDto.ProviderServiceRequestDTO;
import com.rendezvous.dto.providerServiceDto.ProviderServiceResponseDTO;
import com.rendezvous.exception.EntityNotFoundException;
import com.rendezvous.exception.ProviderNotFoundException;
import com.rendezvous.exception.ServiceNotFoundException;
import com.rendezvous.mapper.ProviderServiceMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProviderServiceService {

    private ProviderProfileRepositoy providerProfileRepository;

    private ProviderServiceMapper providerServiceMapper;

    private ProviderServiceRepository providerServiceRepository;

    public ProviderServiceService(ProviderProfileRepositoy providerProfileRepositoy,
                                  ProviderServiceMapper providerServiceMapper,
                                  ProviderServiceRepository providerServiceRepository
                                  ){
        this.providerProfileRepository = providerProfileRepositoy;
        this.providerServiceMapper = providerServiceMapper;
        this.providerServiceRepository = providerServiceRepository;
    }


    /**
     * A method for creating a new type of service.
     * @param serviceDTO is the serviceDTO is the received object.
     * @return Returns the type of service that was saved.
     * @throws throws the exception ProviderNotFoundException if the provider does not exist
     * */

    @Transactional
    public ProviderServiceResponseDTO createService(ProviderServiceRequestDTO serviceDTO){
        ProviderProfile provider = providerProfileRepository.findById(serviceDTO.getProviderId())
                .orElseThrow(()-> new ProviderNotFoundException());

        ProviderService service = providerServiceMapper.toEntity(serviceDTO, provider);
        ProviderService serviceSaved = providerServiceRepository.save(service);
        return providerServiceMapper.toResponseDTO(serviceSaved);
    }

    /**
     * Method for modifying a service type.
     * @param providerServiceId is the providerService ID and providerServiceDTO is the received object.
     * @return returns the type of service that was modified and transformed into a DTO.
     * @throws throws the exception ServiceNotFoundException if the providerService does not exist
     * */

    @Transactional
    public ProviderServiceResponseDTO modifyProviderService(ProviderServiceRequestDTO providerServiceDTO, Long providerServiceId){
        ProviderService providerService = providerServiceRepository.findById(providerServiceId)
                .orElseThrow(()-> new ServiceNotFoundException());
        BeanUtils.copyProperties(providerServiceDTO, providerService, "id");

        return providerServiceMapper.toResponseDTO(providerService);
    }

    /**
     * Method for excluding a type of service.
     * @param providerServiceId is the providerService ID
     * @throws throws the exception ServiceNotFoundException if the providerService does not exist
     * */

    @Transactional
    public void deleteProviderService(Long providerServiceId){
        ProviderService providerService = providerServiceRepository.findById(providerServiceId)
                .orElseThrow(()-> new ServiceNotFoundException());
        providerServiceRepository.delete(providerService);
    }

    /**
     * A method that brings together all the services of a provider.
     * @param providerId is the provider ID
     * @return returns a list of all services from that provider.
     * @throws throws the exception EntityNotFoundException if the provider does not exist
     * */

    @Transactional
    public List<ProviderServiceResponseDTO> findServicesAllByProvider(Long providerId){
        ProviderProfile provider = providerProfileRepository.findById(providerId)
                .orElseThrow(()-> new ProviderNotFoundException());

        List<ProviderService> services = providerServiceRepository.findByProviderId(providerId);
        return services.stream()
                .map(service -> providerServiceMapper.toResponseDTO(service))
                .toList();
    }
}
