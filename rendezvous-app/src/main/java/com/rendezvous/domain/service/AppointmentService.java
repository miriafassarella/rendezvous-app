package com.rendezvous.domain.service;

import com.rendezvous.domain.enums.Status;
import com.rendezvous.domain.model.Appointment;
import com.rendezvous.domain.model.ClientProfile;
import com.rendezvous.domain.model.ProviderProfile;
import com.rendezvous.domain.model.ProviderService;
import com.rendezvous.domain.repository.*;
import com.rendezvous.dto.appointmentDto.AppointmentRequestDTO;
import com.rendezvous.dto.appointmentDto.AppointmentResponseDTO;
import com.rendezvous.mapper.AppointmentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AppointmentService {

    AppointmentRepository appointmentRepository;

    ClientProfileRepository clientProfileRepository;

    ProviderProfileRepositoy providerProfileRepositoy;

    AvailabilityRepository availabilityRepository;

    ProviderServiceRepository providerServiceRepository;

    AppointmentMapper appointmentMapper;

    public AppointmentService(AppointmentRepository appointmentRepository,
                              ClientProfileRepository clientProfileRepository,
                              ProviderProfileRepositoy providerProfileRepositoy,
                              AvailabilityRepository availabilityRepository,
                              ProviderServiceRepository providerServiceRepository,
                              AppointmentMapper appointmentMapper){
    }


    @Transactional
    public List<AppointmentResponseDTO> findAppointmentsAll(){
        List<Appointment> appointments =  appointmentRepository.findAll();
        return appointments.stream()
                .map(appointment -> appointmentMapper.toResponseDTO(appointment))
                .toList();
    }

    @Transactional
    public AppointmentResponseDTO createAppointment(AppointmentRequestDTO appointmentDTO){

        ProviderProfile provider = providerProfileRepositoy.findById(appointmentDTO.getProviderId())
                .orElseThrow(() -> new RuntimeException("Provider not found"));

        ClientProfile client = clientProfileRepository.findById(appointmentDTO.getClientId())
                .orElseThrow(() -> new RuntimeException("Client not found"));

        ProviderService service = providerServiceRepository.findById(appointmentDTO.getServiceId())
                .orElseThrow(() -> new RuntimeException("Service type not found"));

        /*Garantindo que o serviço pertence ao provider*/
        if (!service.getProvider().getId().equals(appointmentDTO.getProviderId())) {
            throw new RuntimeException("Service does not belong to this provider");
        }

        /*aplica LOCK no banco - bloqueia todos os agendamentos para este provider para este dia enquanto
        * a transação esta sendo feita*/
        List<Appointment> conflictingAppointments =
                appointmentRepository.findConflictingAppointmentsForLock(provider, appointmentDTO.getDayOfWeek(),
                        appointmentDTO.getStartTime(), appointmentDTO.getEndTime());

        /*exceção se un agendamento esta dentro do horaio de outro agendamento já existente*/
        if (!conflictingAppointments.isEmpty()) {
            throw new RuntimeException("Time slot already booked");
        }
        /*garantindo que o provider possui o horario disponível*/
        boolean available = availabilityRepository
                .existsByProviderAndDayOfWeekAndStartTimeLessThanEqualAndEndTimeGreaterThanEqual(
                        provider, appointmentDTO.getDayOfWeek(), appointmentDTO.getStartTime(),
                        appointmentDTO.getStartTime().plusMinutes(service.getDuration_minutes())
                );

        if (!available) {
            throw new RuntimeException("The chosen time or day is not available.");
            //exception
        }

        Appointment appointment = appointmentMapper.toEntity(appointmentDTO, provider, client, service);
        appointment.setStatus(Status.PENDING);
        Appointment appointmentSaved = appointmentRepository.save(appointment);

        return appointmentMapper.toResponseDTO(appointmentSaved);

    }

}
