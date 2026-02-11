package com.rendezvous.domain.service;

import com.rendezvous.domain.model.Availability;
import com.rendezvous.domain.model.ProviderProfile;
import com.rendezvous.domain.repository.AvailabilityRepository;
import com.rendezvous.domain.repository.ProviderProfileRepositoy;
import com.rendezvous.dto.availabilityDto.AvailabilityRequestDTO;
import com.rendezvous.dto.availabilityDto.AvailabilityResponseDTO;
import com.rendezvous.mapper.AvailabilityMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class AvailabilityService {

    private ProviderProfileRepositoy providerProfileRepositoy;

    private AvailabilityRepository availabilityRepository;

    private AvailabilityMapper availabilityMapper;

    public AvailabilityService(ProviderProfileRepositoy providerProfileRepositoy,
                               AvailabilityRepository availabilityRepository,
                               AvailabilityMapper availabilityMapper){

    }

    @Transactional
    public AvailabilityResponseDTO createAvailability(AvailabilityRequestDTO availabilityDTO){

        Optional<ProviderProfile> provider = providerProfileRepositoy.findById(availabilityDTO.getProviderId());
        Availability availability = availabilityMapper.toEntity(availabilityDTO, provider.get());
        Availability availabilitySaved = availabilityRepository.save(availability);

        return availabilityMapper.toResponseDTO(availabilitySaved);

    }

}
