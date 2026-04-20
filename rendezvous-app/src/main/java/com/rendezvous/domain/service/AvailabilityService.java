package com.rendezvous.domain.service;

import com.rendezvous.domain.model.Availability;
import com.rendezvous.domain.model.ProviderProfile;
import com.rendezvous.domain.model.User;
import com.rendezvous.domain.repository.AppointmentRepository;
import com.rendezvous.domain.repository.AvailabilityRepository;
import com.rendezvous.domain.repository.ProviderProfileRepositoy;
import com.rendezvous.dto.availabilityDto.AvailabilityRequestDTO;
import com.rendezvous.dto.availabilityDto.AvailabilityResponseDTO;
import com.rendezvous.exception.*;
import com.rendezvous.mapper.AvailabilityMapper;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AvailabilityService {

    private ProviderProfileRepositoy providerProfileRepositoy;

    private AvailabilityRepository availabilityRepository;

    private AvailabilityMapper availabilityMapper;

    private AppointmentRepository appointmentRepository;

    public AvailabilityService(ProviderProfileRepositoy providerProfileRepositoy,
                               AvailabilityRepository availabilityRepository,
                               AvailabilityMapper availabilityMapper,
                               AppointmentRepository appointmentRepository){

        this.providerProfileRepositoy = providerProfileRepositoy;
        this.availabilityRepository = availabilityRepository;
        this.availabilityMapper = availabilityMapper;
        this.appointmentRepository = appointmentRepository;
    }

    @Transactional
    public List<AvailabilityResponseDTO> findAvailabilityAll(){
        List<Availability> availabilities = availabilityRepository.findAll();
        return availabilities.stream()
                .map(availability -> availabilityMapper.toResponseDTO(availability))
                .toList();
    }

    @Transactional
    public AvailabilityResponseDTO createAvailability(AvailabilityRequestDTO availabilityDTO){

        ProviderProfile provider = providerProfileRepositoy.findById(availabilityDTO.getProviderId())
                .orElseThrow(()-> new ProviderNotFoundException());

        //impedindo que duas disponibilidades iguais sejam registradas para o mesmo provider
        //preciso modificar
        boolean available = availabilityRepository.existsByProviderAndDayOfWeekAndStartTimeLessThanEqualAndEndTimeGreaterThanEqual(
            provider, availabilityDTO.getDayOfWeek(), availabilityDTO.getStartTime(), availabilityDTO.getEndTime()
        );
        if (available){
            throw  new AvailabilityFoundException();
        }
        Availability availability = availabilityMapper.toEntity(availabilityDTO, provider);
        Availability availabilitySaved = availabilityRepository.save(availability);

        return availabilityMapper.toResponseDTO(availabilitySaved);
    }

    @Transactional
    public AvailabilityResponseDTO modifyAvailability(AvailabilityRequestDTO availabilityDTO, Long availabilityId){
        Availability availability = availabilityRepository.findById(availabilityId)
                .orElseThrow(()-> new AvailabilityNotFoundException());


        boolean available = availabilityRepository.existsByProviderAndDayOfWeekAndStartTimeLessThanEqualAndEndTimeGreaterThanEqual(
                availability.getProvider(), availabilityDTO.getDayOfWeek(), availabilityDTO.getStartTime(), availabilityDTO.getEndTime()
        );
        if (available){
            throw  new AvailabilityFoundException();
        }

        availability.setDayOfWeek(availabilityDTO.getDayOfWeek());
        availability.setStartTime(availabilityDTO.getStartTime());
        availability.setEndTime(availabilityDTO.getEndTime());

        return availabilityMapper.toResponseDTO(availability);
    }

    @Transactional
    public void deleteAvailability(Long availabilityId){
        Availability availability = availabilityRepository.findById(availabilityId)
                .orElseThrow(()-> new AvailabilityNotFoundException());
        boolean hasAppointment = appointmentRepository.existsByProviderAndDayOfWeek(availability.getProvider(),
                availability.getDayOfWeek());
        if (hasAppointment){
            throw new AvailabilityInUseException();
        }
        availabilityRepository.delete(availability);
    }

    @Transactional
    public List<AvailabilityResponseDTO> findByProviderId(Long providerId, User loggedUser){
        ProviderProfile provider = providerProfileRepositoy.findByUserId(loggedUser.getId())
                .orElseThrow(()-> new ProviderNotFoundException());

        if (!provider.getId().equals(providerId)){
            throw new AccessDeniedException();
        }
        List<Availability> availabilities = availabilityRepository.findByProvider(provider);
        return availabilities.stream()
                .map(availability-> availabilityMapper.toResponseDTO(availability))
                .toList();
    }

}
