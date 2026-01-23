package com.rendezvous.domain.service;

import com.rendezvous.domain.model.Appointment;
import com.rendezvous.domain.model.ClientProfile;
import com.rendezvous.domain.model.ProviderProfile;
import com.rendezvous.domain.model.ProviderService;
import com.rendezvous.domain.repository.*;
import com.rendezvous.dto.AppointmentDto.AppointmentRequestDTO;
import com.rendezvous.dto.AppointmentDto.AppointmentResponseDTO;
import com.rendezvous.mapper.AppointmentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AppointmentService {

    @Autowired
    AppointmentRepository appointmentRepository;

    @Autowired
    ClientProfileRepository clientProfileRepository;

    @Autowired
    ProviderProfileRepositoy providerProfileRepositoy;

    @Autowired
    AvailabilityRepository availabilityRepository;

    @Autowired
    ServiceRepository serviceRepository;

    @Autowired
    AppointmentMapper appointmentMapper;

    public AppointmentResponseDTO createAppointment(AppointmentRequestDTO appointmentDTO){

        Optional<ClientProfile> client = clientProfileRepository.findById(appointmentDTO.getClientId());
        Optional<ProviderProfile> provider = providerProfileRepositoy.findById(appointmentDTO.getProviderId());
        Optional<ProviderService> service = serviceRepository.findById(appointmentDTO.getServiceId());

        if(client.isEmpty() || provider.isEmpty() || !service.get().getProvider().getId().equals(appointmentDTO.getProviderId())) {

            //TODO
            //À modifier an ajoutant exception

        }
        Appointment appointment = appointmentMapper.toEntity(appointmentDTO, provider.get(), client.get(), service.get());

        boolean available = availabilityRepository
                .existsByProviderAndDayOfWeekAndStartTimeLessThanEqualAndEndTimeGreaterThanEqual(
                        provider.get(), appointmentDTO.getDayOfWeek(), appointmentDTO.getStartTime(),
                        appointmentDTO.getEndTime()
                );

        if (!available) {
            //exception
        }

            Appointment appointmentSaved = appointmentRepository.save(appointment);

            return appointmentMapper.toResponseDTO(appointmentSaved);

    }

}
