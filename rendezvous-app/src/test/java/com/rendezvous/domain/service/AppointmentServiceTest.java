package com.rendezvous.domain.service;

import com.rendezvous.domain.model.Appointment;
import com.rendezvous.domain.model.ClientProfile;
import com.rendezvous.domain.repository.AppointmentRepository;
import com.rendezvous.domain.repository.ClientProfileRepository;
import com.rendezvous.domain.repository.RoleRepository;
import com.rendezvous.domain.repository.UserRepository;
import com.rendezvous.dto.appointmentDto.AppointmentResponseDTO;
import com.rendezvous.dto.clientProfileDto.ClientProfileResponseDTO;
import com.rendezvous.mapper.ClientProfileMapper;
import com.rendezvous.mapper.UserMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.time.DayOfWeek;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class AppointmentServiceTest {

    @Mock
    private ClientProfileRepository clientProfileRepository;

    @Mock
    private UserRepository userRepository;
    @Mock
    private RoleRepository roleRepository;
    @Mock
    private AppointmentRepository appointmentRepository;
    @Mock
    private ClientProfileMapper clientProfileMapper;
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


    }

}