package com.rendezvous.mapper;

import com.rendezvous.domain.model.Appointment;
import com.rendezvous.domain.model.ClientProfile;
import com.rendezvous.domain.model.ProviderProfile;
import com.rendezvous.domain.model.TypeOfService;
import com.rendezvous.dto.AppointmentDto.AppointmentRequestDTO;
import com.rendezvous.dto.AppointmentDto.AppointmentResponseDTO;
import org.springframework.stereotype.Component;

@Component
public class AppointmentMapper {

    public Appointment toEntity(AppointmentRequestDTO appointmentDTO, ProviderProfile provider, ClientProfile client,
                                TypeOfService service){
        Appointment appointment = new Appointment();
        appointment.setStartTime(appointmentDTO.getStartTime());
        appointment.setEndTime(appointmentDTO.getEndTime());
        appointment.setClient(client);
        appointment.setProvider(provider);
        appointment.setService(service);

        return appointment;
    }

    public AppointmentResponseDTO toResponseDTO(Appointment appointment){
        AppointmentResponseDTO appointmentResponseDTO = new AppointmentResponseDTO();
        appointmentResponseDTO.setClientId(appointment.getId());
        appointmentResponseDTO.setStartTime(appointment.getStartTime());
        appointmentResponseDTO.setEndTime(appointment.getEndTime());
        appointmentResponseDTO.setClientId(appointment.getClient().getId());
        appointmentResponseDTO.setProviderId(appointment.getProvider().getId());
        appointmentResponseDTO.setServiceId(appointment.getService().getId());

        return appointmentResponseDTO;
    }
}
