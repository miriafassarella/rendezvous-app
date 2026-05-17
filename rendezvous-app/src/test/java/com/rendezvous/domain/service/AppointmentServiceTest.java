package com.rendezvous.domain.service;

import com.rendezvous.domain.model.Appointment;
import com.rendezvous.domain.model.ClientProfile;
import com.rendezvous.domain.model.ProviderProfile;
import com.rendezvous.domain.model.ProviderService;
import com.rendezvous.domain.repository.*;
import com.rendezvous.dto.appointmentDto.AppointmentRequestDTO;
import com.rendezvous.dto.appointmentDto.AppointmentResponseDTO;

import com.rendezvous.dto.clientProfileDto.ClientProfileRequestDTO;
import com.rendezvous.exception.ClientNotFoundException;
import com.rendezvous.exception.InvalidProviderServiceException;
import com.rendezvous.exception.ProviderNotFoundException;
import com.rendezvous.exception.ServiceNotFoundException;
import com.rendezvous.mapper.AppointmentMapper;

import com.rendezvous.mapper.UserMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class AppointmentServiceTest {

    @Mock
    private ClientProfileRepository clientProfileRepository;

    @Mock
    private ProviderProfileRepositoy providerProfileRepositoy;

    @Mock
    private AppointmentRepository appointmentRepository;
    @Mock
    private AppointmentMapper appointmentMapper;
    @Mock
    private ProviderServiceRepository providerServiceRepository;

    @InjectMocks
    private AppointmentService appointmentService;
    private Appointment appointment;
    private AppointmentResponseDTO appointmentResponseDTO;
    private ProviderProfile providerProfile;
    private ClientProfile client;
    private ProviderService service;

    @BeforeEach
    void setUp(){
        appointment = new Appointment();
        appointment.setDayOfWeek(DayOfWeek.MONDAY);

        AppointmentResponseDTO appointment = new AppointmentResponseDTO();

        providerProfile = new ProviderProfile();
        providerProfile.setId(1L);

        client = new ClientProfile();
        client.setId(1L);

        service = new ProviderService();
        service.setId(1L);
        service.setProvider(providerProfile);
    }


    @Test
    void shouldReturnAppointmentsList_whenAppointmentsExist(){
            when(appointmentRepository.findAll()).thenReturn(List.of(appointment));
            when(appointmentMapper.toResponseDTO(appointment)).thenReturn(appointmentResponseDTO);

            List<AppointmentResponseDTO> appointments = appointmentService.findAppointmentsAll();

            assertThat(appointments).hasSize(1);

            verify(appointmentRepository, times(1)).findAll();
    }

    @Test
    void shouldThrowProviderNotFoundException_whenProviderNotFound(){
        when(providerProfileRepositoy.findById(99L)).thenReturn(Optional.empty());

        AppointmentRequestDTO request = new AppointmentRequestDTO();
        request.setProviderId(99L);

        assertThatThrownBy(()-> appointmentService.createAppointment(request))
                .isInstanceOf(ProviderNotFoundException.class);

        verify(providerProfileRepositoy, times(1)).findById(99L);

    }

    @Test
    void shouldThrowClientNotFoundException_whenClientNotFound(){
        when(providerProfileRepositoy.findById(1L)).thenReturn(Optional.of(providerProfile));
        when(clientProfileRepository.findById(99L)).thenReturn(Optional.empty());

        AppointmentRequestDTO request = new AppointmentRequestDTO();
        request.setProviderId(1L);
        request.setClientId(99L);

        assertThatThrownBy(()-> appointmentService.createAppointment(request))
                .isInstanceOf(ClientNotFoundException.class);

        verify(providerProfileRepositoy, times(1)).findById(1L);
        verify(clientProfileRepository, times(1)).findById(99L);

    }

    @Test
    void shouldThrowServiceNotFoundException_whenServiceNotFound(){
        when(providerProfileRepositoy.findById(1L)).thenReturn(Optional.of(providerProfile));
        when(clientProfileRepository.findById(1L)).thenReturn(Optional.of(client));
        when(providerServiceRepository.findById(99L)).thenReturn(Optional.empty());

        AppointmentRequestDTO request = new AppointmentRequestDTO();
        request.setProviderId(1L);
        request.setClientId(1L);
        request.setServiceId(99L);

        assertThatThrownBy(()-> appointmentService.createAppointment(request))
                .isInstanceOf(ServiceNotFoundException.class);

        verify(providerProfileRepositoy, times(1)).findById(1L);
        verify(clientProfileRepository, times(1)).findById(1L);
        verify(providerServiceRepository, times(1)).findById(99L);
    }

    @Test
    void shouldThrowInvalidProviderServiceException_whenProviderNotHaveService(){
        when(providerProfileRepositoy.findById(99L)).thenReturn(Optional.of(providerProfile));
        when(clientProfileRepository.findById(1L)).thenReturn(Optional.of(client));
        when(providerServiceRepository.findById(1L)).thenReturn(Optional.of(service));


        AppointmentRequestDTO request = new AppointmentRequestDTO();
        request.setProviderId(99L);
        request.setClientId(1L);
        request.setServiceId(1L);

        assertThatThrownBy(()-> appointmentService.createAppointment(request))
                .isInstanceOf(InvalidProviderServiceException.class);

        verify(providerProfileRepositoy, times(1)).findById(99L);
        verify(clientProfileRepository, times(1)).findById(1L);
        verify(providerServiceRepository, times(1)).findById(1L);

    }
}