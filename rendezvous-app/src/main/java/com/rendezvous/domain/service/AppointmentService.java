package com.rendezvous.domain.service;

import com.rendezvous.domain.model.Appointment;
import com.rendezvous.domain.model.ClientProfile;
import com.rendezvous.domain.model.ProviderProfile;
import com.rendezvous.domain.model.TypeOfService;
import com.rendezvous.domain.repository.AppointmentRepository;
import com.rendezvous.domain.repository.ClientProfileRepository;
import com.rendezvous.domain.repository.ProviderProfileRepositoy;
import com.rendezvous.domain.repository.ServiceRepository;
import com.rendezvous.dto.AppointmentDto.AppointmentRequestDTO;
import com.rendezvous.dto.AppointmentDto.AppointmentResponseDTO;
import com.rendezvous.mapper.AppointmentMapper;
import org.springframework.beans.BeanUtils;
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
    ServiceRepository serviceRepository;

    @Autowired
    AppointmentMapper appointmentMapper;

    public AppointmentResponseDTO createAppointment(AppointmentRequestDTO appointmentDTO){

        Optional<ClientProfile> client = clientProfileRepository.findById(appointmentDTO.getClientId());
        Optional<ProviderProfile> provider = providerProfileRepositoy.findById(appointmentDTO.getProviderId());
        Optional<TypeOfService> service = serviceRepository.findById(appointmentDTO.getServiceId());

        Appointment appointment = appointmentMapper.toEntity(appointmentDTO, provider.get(), client.get(), service.get());
        Appointment appointmentSaved = appointmentRepository.save(appointment);

        return appointmentMapper.toResponseDTO(appointmentSaved);

    }

}
