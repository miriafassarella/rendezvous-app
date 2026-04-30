package com.rendezvous.domain.service;

import com.rendezvous.domain.model.ClientProfile;
import com.rendezvous.domain.repository.AppointmentRepository;
import com.rendezvous.domain.repository.ClientProfileRepository;
import com.rendezvous.domain.repository.RoleRepository;
import com.rendezvous.domain.repository.UserRepository;
import com.rendezvous.dto.clientProfileDto.ClientProfileResponseDTO;
import com.rendezvous.exception.ClientNotFoundException;
import com.rendezvous.mapper.ClientProfileMapper;
import com.rendezvous.mapper.UserMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(MockitoExtension.class)
class ClientProfileServiceTest {

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
    private ClientProfileService clientProfileService;
    private ClientProfile clientProfile;
    private ClientProfileResponseDTO clientProfileResponseDTO;

    @BeforeEach
    void setUp(){
      clientProfile = new ClientProfile();
      clientProfile.setFirstName("Mia");
      clientProfile.setLastName("Fassarela");

      clientProfileResponseDTO = new ClientProfileResponseDTO();
    }

    @Test
    void shouldReturnClientList_whenClientsExist(){
        //arrange
        when(clientProfileRepository.findAll()).thenReturn(List.of(clientProfile));
        when(clientProfileMapper.toResponseDTO(clientProfile)).thenReturn(clientProfileResponseDTO);

        //act
        List<ClientProfileResponseDTO> result = clientProfileService.findClientAll();

        //assert
        assertThat(result).hasSize(1);
        verify(clientProfileRepository, times(1)).findAll();

    }


    @Test
    void shouldReturnEmptyList_whenNoClientsExist(){
        when(clientProfileRepository.findAll()).thenReturn(List.of());

        List<ClientProfileResponseDTO> result = clientProfileService.findClientAll();


        assertThat(result).isEmpty();

    }

    @Test
    void shouldReturnClient_whenClientExist(){

        when(clientProfileRepository.findById(1L)).thenReturn(Optional.of(clientProfile));
        when(clientProfileMapper.toResponseDTO(clientProfile)).thenReturn(clientProfileResponseDTO);

        ClientProfileResponseDTO result = clientProfileService.findById(1L);

        assertThat(result).isNotNull();

    }

    @Test
    void shouldThrowClientNotFoundException_whenClientNotFound(){
        when(clientProfileRepository.findById(99L)).thenReturn(Optional.empty());

       assertThatThrownBy(()-> clientProfileService.findById(99L))
               .isInstanceOf(ClientNotFoundException.class);

    }



}