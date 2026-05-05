package com.rendezvous.domain.service;
import com.rendezvous.domain.model.ProviderProfile;
import com.rendezvous.domain.repository.*;
import com.rendezvous.dto.providerProfileDto.ProviderProfileResponseDTO;
import com.rendezvous.exception.ProviderNotFoundException;
import com.rendezvous.mapper.ProviderProfileMapper;
import com.rendezvous.mapper.UserMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class ProviderProfileServiceTest {

    @Mock
    private ProviderProfileRepositoy providerProfileRepositoy;

    @Mock
    private UserRepository userRepository;
    @Mock
    private RoleRepository roleRepository;
    @Mock
    private AppointmentRepository appointmentRepository;
    @Mock
    private ProviderProfileMapper providerProfileMapper;
    @Mock
    private UserMapper userMapper;

    @InjectMocks
    private ProviderProfileService providerProfileService;
    private ProviderProfile providerProfile;
    private ProviderProfileResponseDTO providerProfileResponseDTO;

    @BeforeEach
    void setUp(){
        providerProfile = new ProviderProfile();
        providerProfile.setCompanyName("Mabra Technologie");

        providerProfileResponseDTO = new ProviderProfileResponseDTO();
    }

    @Test
    void shouldReturnProviderList_whenProviderExist(){
        when(providerProfileRepositoy.findAll()).thenReturn(List.of(providerProfile));
        when(providerProfileMapper.toResponseDTO(providerProfile)).thenReturn(providerProfileResponseDTO);

        List<ProviderProfileResponseDTO> result = providerProfileService.findProviderAll();

        assertThat(result).hasSize(1);
        verify(providerProfileRepositoy, times(1)).findAll();
    }

    @Test
    void shouldReturnEmptyList_whenNoProviderExist(){
        when(providerProfileRepositoy.findAll()).thenReturn(List.of());

        List<ProviderProfileResponseDTO> result = providerProfileService.findProviderAll();

        assertThat(result).isEmpty();
    }

    @Test
    void shouldReturnprovider_whenProviderExist(){
        when(providerProfileRepositoy.findById(1L)).thenReturn(Optional.of(providerProfile));
        when(providerProfileMapper.toResponseDTO(providerProfile)).thenReturn(providerProfileResponseDTO);

        ProviderProfileResponseDTO result = providerProfileService.findById(1L);

        assertThat(result).isNotNull();
    }

    @Test
    void shouldThrowProviderNotFoundException_whenProviderNotFound(){
        when(providerProfileRepositoy.findById(99L)).thenReturn(Optional.empty());

        assertThatThrownBy(()-> providerProfileService.findById(99L))
                .isInstanceOf(ProviderNotFoundException.class);
    }

    @Test
    void shouldDeleteClient_whenClientHasNoAppointments(){
        when(providerProfileRepositoy.findById(1L)).thenReturn(Optional.of(providerProfile));
       when(appointmentRepository.existsByProvider(providerProfile)).thenReturn(false);

       providerProfileService.deleteProvider(1L);

       verify(providerProfileRepositoy, times(1)).delete(providerProfile);
    }
}