package com.rendezvous.mapper;

import com.rendezvous.domain.model.Appointment;
import com.rendezvous.domain.model.ClientProfile;
import com.rendezvous.domain.model.ProviderProfile;
import com.rendezvous.domain.model.ProviderService;
import com.rendezvous.dto.AppointmentDto.AppointmentRequestDTO;
import com.rendezvous.dto.AppointmentDto.AppointmentResponseDTO;
import org.springframework.stereotype.Component;

@Component
public class AppointmentMapper {

    public Appointment toEntity(AppointmentRequestDTO appointmentDTO, ProviderProfile provider, ClientProfile client,
                                ProviderService service){
        Appointment appointment = new Appointment();
        appointment.setStartTime(appointmentDTO.getStartTime());
        appointment.setEndTime(appointmentDTO.getStartTime().plusMinutes(service.getDuration_minutes()));
        appointment.setDayOfWeek(appointmentDTO.getDayOfWeek());
        appointment.setClient(client);
        appointment.setProvider(provider);
        appointment.setService(service);
        return appointment;
    }

    public AppointmentResponseDTO toResponseDTO(Appointment appointment){
        return new AppointmentResponseDTO(appointment.getId(), appointment.getDayOfWeek(),
                appointment.getStartTime(), appointment.getEndTime(), appointment.getService().getId(),
                appointment.getProvider().getId(), appointment.getClient().getId(), appointment.getStatus());

    }
}
