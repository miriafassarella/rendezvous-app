package com.rendezvous.domain.service;

import com.rendezvous.domain.model.Appointment;
import com.rendezvous.domain.model.ClientProfile;
import com.rendezvous.domain.repository.*;
import com.rendezvous.dto.appointmentDto.AppointmentRequestDTO;
import com.rendezvous.dto.appointmentDto.AppointmentResponseDTO;

import com.rendezvous.exception.ProviderNotFoundException;
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
    private UserRepository userRepository;
    @Mock
    private RoleRepository roleRepository;
    @Mock
    private AppointmentRepository appointmentRepository;
    @Mock
    private AppointmentMapper appointmentMapper;
    @Mock
    private UserMapper userMapper;

    @InjectMocks
    private AppointmentService appointmentService;
    private Appointment appointment;
    private AppointmentResponseDTO appointmentResponseDTO;

    @BeforeEach
    void setUp(){
        appointment = new Appointment();
        appointment.setDayOfWeek(DayOfWeek.MONDAY);

        AppointmentResponseDTO appointment = new AppointmentResponseDTO();
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
}