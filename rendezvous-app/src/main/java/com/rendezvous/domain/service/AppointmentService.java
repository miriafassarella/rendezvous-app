package com.rendezvous.domain.service;

import com.rendezvous.domain.enums.Status;
import com.rendezvous.domain.model.Appointment;
import com.rendezvous.domain.model.ClientProfile;
import com.rendezvous.domain.model.ProviderProfile;
import com.rendezvous.domain.model.ProviderService;
import com.rendezvous.domain.repository.*;
import com.rendezvous.dto.appointmentDto.AppointmentRequestDTO;
import com.rendezvous.dto.appointmentDto.AppointmentResponseDTO;
import com.rendezvous.exception.*;
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
        this.appointmentRepository = appointmentRepository;
        this.clientProfileRepository = clientProfileRepository;
        this.providerProfileRepositoy = providerProfileRepositoy;
        this.availabilityRepository = availabilityRepository;
        this.providerServiceRepository = providerServiceRepository;
        this.appointmentMapper = appointmentMapper;
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
                .orElseThrow(() -> new ProviderNotFoundException());

        ClientProfile client = clientProfileRepository.findById(appointmentDTO.getClientId())
                .orElseThrow(() -> new ClientNotFoundException());

        ProviderService service = providerServiceRepository.findById(appointmentDTO.getServiceId())
                .orElseThrow(() -> new ServiceNotFoundException());

        /*Garantindo que o serviço pertence ao provider*/
        if (!service.getProvider().getId().equals(appointmentDTO.getProviderId())) {
            throw new InvalidProviderServiceException();
        }

        /*aplica LOCK no banco - bloqueia todos os agendamentos para este provider para este dia enquanto
        * a transação esta sendo feita*/
        List<Appointment> conflictingAppointments =
                appointmentRepository.findConflictingAppointmentsForLock(provider, appointmentDTO.getDayOfWeek(),
                        appointmentDTO.getStartTime(), appointmentDTO.getStartTime().plusMinutes(service.getDuration_minutes()));


        /*exceção se un agendamento esta dentro do horaio de outro agendamento já existente*/
        if (!conflictingAppointments.isEmpty()) {
            throw new TimeSlotAlreadyBookedException();
        }
        /*garantindo que o provider possui o horario disponível*/
        boolean available = availabilityRepository
                .existsByProviderAndDayOfWeekAndStartTimeLessThanEqualAndEndTimeGreaterThanEqual(
                        provider, appointmentDTO.getDayOfWeek(), appointmentDTO.getStartTime(),
                        appointmentDTO.getStartTime().plusMinutes(service.getDuration_minutes())
                );

        if (!available) {
            throw new ProviderNotAvailableException();
        }

        Appointment appointment = appointmentMapper.toEntity(appointmentDTO, provider, client, service);
        appointment.setStatus(Status.PENDING);
        Appointment appointmentSaved = appointmentRepository.save(appointment);

        return appointmentMapper.toResponseDTO(appointmentSaved);

    }

    @Transactional
    public List<AppointmentResponseDTO> findByProviderId(Long providerId){
        ProviderProfile provider = providerProfileRepositoy.findById(providerId)
                .orElseThrow(()-> new ProviderNotFoundException());

        List<Appointment> appointments = appointmentRepository.findAllByProvider_Id(provider.getId());

        return appointments.stream()
                .map(appointment -> appointmentMapper.toResponseDTO(appointment))
                .toList();

    }

    @Transactional
    public  List<AppointmentResponseDTO> findByClientId(Long clientId){
        ClientProfile client = clientProfileRepository.findById(clientId)
                .orElseThrow(()-> new ClientNotFoundException());

        List<Appointment> appointments = appointmentRepository.findAllByClient_Id(client.getId());
        return appointments.stream()
                .map(appointment-> appointmentMapper.toResponseDTO(appointment))
                .toList();
    }

}
