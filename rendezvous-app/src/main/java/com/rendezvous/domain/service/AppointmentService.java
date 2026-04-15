package com.rendezvous.domain.service;

import com.rendezvous.domain.enums.Status;
import com.rendezvous.domain.model.*;
import com.rendezvous.domain.repository.*;
import com.rendezvous.dto.appointmentDto.AppointmentRequestDTO;
import com.rendezvous.dto.appointmentDto.AppointmentResponseDTO;
import com.rendezvous.exception.*;
import com.rendezvous.mapper.AppointmentMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class AppointmentService {

    AppointmentRepository appointmentRepository;

    ClientProfileRepository clientProfileRepository;

    ProviderProfileRepositoy providerProfileRepository;

    AvailabilityRepository availabilityRepository;

    ProviderServiceRepository providerServiceRepository;

    AppointmentMapper appointmentMapper;

    public AppointmentService(AppointmentRepository appointmentRepository,
                              ClientProfileRepository clientProfileRepository,
                              ProviderProfileRepositoy providerProfileRepository,
                              AvailabilityRepository availabilityRepository,
                              ProviderServiceRepository providerServiceRepository,
                              AppointmentMapper appointmentMapper){
        this.appointmentRepository = appointmentRepository;
        this.clientProfileRepository = clientProfileRepository;
        this.providerProfileRepository = providerProfileRepository;
        this.availabilityRepository = availabilityRepository;
        this.providerServiceRepository = providerServiceRepository;
        this.appointmentMapper = appointmentMapper;
    }

    @Transactional
    public List<AppointmentResponseDTO> findAppointmentsAll(){
        List<Appointment> appointments =  appointmentRepository.findAll();

        return appointments.stream()
                .map(appointmentMapper::toResponseDTO)
                /*.map(appointment-> appointmentmapper.toResponseDTO(appointment))*/
                .toList();
    }

    @Transactional
    public AppointmentResponseDTO createAppointment(AppointmentRequestDTO appointmentDTO){

        ProviderProfile provider = providerProfileRepository.findById(appointmentDTO.getProviderId())
                .orElseThrow(() -> new ProviderNotFoundException());

        ClientProfile client = clientProfileRepository.findById(appointmentDTO.getClientId())
                .orElseThrow(() -> new ClientNotFoundException());

        ProviderService service = providerServiceRepository.findById(appointmentDTO.getServiceId())
                .orElseThrow(() -> new ServiceNotFoundException());

        /*Garantindo que o serviço pertence ao provider*/
        if (!service.getProvider().getId().equals(appointmentDTO.getProviderId())) {
            throw new InvalidProviderServiceException();
        }

        /*garantindo usando keywords que o provider possui disponibilidade neste período*/
        boolean available = availabilityRepository
                . existsByProviderAndDayOfWeekAndStartTimeLessThanEqualAndEndTimeGreaterThanEqual(
                        provider, appointmentDTO.getStartTime().getDayOfWeek(), appointmentDTO.getStartTime().toLocalTime(),
                        appointmentDTO.getStartTime().plusMinutes(service.getDuration_minutes()).toLocalTime()
                );


        if (!available) {
            throw new ProviderNotAvailableException();
        }

        //impedindo que um appointment seja agendado em uma data e horário anterior
        LocalDateTime now = LocalDateTime.now().plusMinutes(30);

        if (appointmentDTO.getStartTime().isBefore(now)) {
            throw new TimeSlotAlreadyBookedException("Appointments must be scheduled at least 30 minutes in advance.");
        }

        /*aplica LOCK no banco - bloqueia todos os agendamentos para este provider para este dia enquanto
        * a transação esta sendo feita*/
        List<Appointment> conflictingAppointments =
                appointmentRepository.findConflictingAppointmentsForLock(provider,
                        appointmentDTO.getStartTime(), appointmentDTO.getStartTime().plusMinutes(service.getDuration_minutes()));

        /*exceção se un agendamento esta dentro do horaio de outro agendamento já existente*/

        if (!conflictingAppointments.isEmpty()) {
            throw new TimeSlotAlreadyBookedException();
        }

        Appointment appointment = appointmentMapper.toEntity(appointmentDTO, provider, client, service);
                appointment.setStatus(Status.PENDING);
                appointment.setDayOfWeek(appointment.getStartTime().getDayOfWeek());
        Appointment appointmentSaved = appointmentRepository.save(appointment);

        return appointmentMapper.toResponseDTO(appointmentSaved);

    }

    @Transactional
    public AppointmentResponseDTO modifyAppointment(AppointmentRequestDTO appointmentDTO, Long appointmentId){
        Appointment appointment = appointmentRepository.findById(appointmentId)
                .orElseThrow(()-> new AppointmentNotFoundException());

        Optional<ProviderProfile> provider = providerProfileRepository.findById(appointmentDTO.getProviderId());
        ProviderService service = providerServiceRepository.findById(appointmentDTO.getServiceId())
                .orElseThrow();
/*Validações -------------------------------------------------------------------------------------------*/
        boolean available = availabilityRepository
                . existsByProviderAndDayOfWeekAndStartTimeLessThanEqualAndEndTimeGreaterThanEqual(
                        provider.get(), appointmentDTO.getStartTime().getDayOfWeek(), appointmentDTO.getStartTime().toLocalTime(),
                        appointmentDTO.getStartTime().plusMinutes(service.getDuration_minutes()).toLocalTime()
                );
        if (!available) {
            throw new ProviderNotAvailableException();
        }
        LocalDateTime now = LocalDateTime.now().plusMinutes(30);
        if (appointmentDTO.getStartTime().isBefore(now)) {
            throw new TimeSlotAlreadyBookedException("Appointments must be scheduled at least 30 minutes in advance.");
        }
        /*aplica LOCK no banco - bloqueia todos os agendamentos para este provider para este dia enquanto
         * a transação esta sendo feita*/
        List<Appointment> conflictingAppointments =
                appointmentRepository.findConflictingAppointmentsForLock(provider.get(),
                        appointmentDTO.getStartTime(), appointmentDTO.getStartTime().plusMinutes(service.getDuration_minutes()));
        /*exceção se un agendamento esta dentro do horaio de outro agendamento já existente*/
        if (!conflictingAppointments.isEmpty()) {
            throw new TimeSlotAlreadyBookedException();
        }
/*--------------------------------------------------------------------------------------------------------------*/
        appointment.setStartTime(appointmentDTO.getStartTime());
        appointment.setEndTime(appointment.getStartTime().plusMinutes(appointment.getService().getDuration_minutes()));
        appointment.getService().setId(appointmentDTO.getServiceId());
        return appointmentMapper.toResponseDTO(appointment);
        //transaction não precisa do metodo save() para uma entidade que já existe no banco
    }

    @Transactional
    public void deleteAppointment(Long appointmentId, User user){
        Appointment appointment = appointmentRepository.findById(appointmentId)
                .orElseThrow(()-> new AppointmentNotFoundException());
        if (!appointment.getClient().getUser().getId().equals(user.getId())){
                throw new AccessDeniedException();
        }
        appointmentRepository.delete(appointment);
    }

    @Transactional
    public List<AppointmentResponseDTO> findByProviderId(User loggedUser) {
        ProviderProfile provider = providerProfileRepository.findByUserId(loggedUser.getId())
                .orElseThrow(()-> new ProviderNotFoundException());
        List<Appointment> appointments = appointmentRepository.findAllByProvider_Id(provider.getId());
        return appointments.stream()
                .map(appointment -> appointmentMapper.toResponseDTO(appointment))
                .toList();
    }

    @Transactional
    public  List<AppointmentResponseDTO> findByClientId(User loggedUser){
        ClientProfile client = clientProfileRepository.findByUserId(loggedUser.getId())
                .orElseThrow(()-> new ClientNotFoundException());
        List<Appointment> appointments = appointmentRepository.findAllByClient_Id(client.getId());
        return appointments.stream()
                .map(appointment-> appointmentMapper.toResponseDTO(appointment))
                .toList();
    }

    /*Esse appointment pertence realmente ao clinte logado?*/
    @Transactional
    public AppointmentResponseDTO canceledAppointment(Long appointmentId){
        Appointment appointment = appointmentRepository.findById(appointmentId)
                .orElseThrow(()-> new AppointmentNotFoundException());
        appointment.cancel();
       Appointment appointmentSaved = appointmentRepository.save(appointment);
       return appointmentMapper.toResponseDTO(appointmentSaved);
    }

    /*Esse appointment pertence realmente ao provider logado?*/
    @Transactional
    public AppointmentResponseDTO confirmedAppointment(Long appointmentId){
        Appointment appointment = appointmentRepository.findById(appointmentId)
                .orElseThrow(()-> new AppointmentNotFoundException());
        appointment.confirm();
       Appointment appointmentSaved =  appointmentRepository.save(appointment);
       return appointmentMapper.toResponseDTO(appointmentSaved);
    }
}
