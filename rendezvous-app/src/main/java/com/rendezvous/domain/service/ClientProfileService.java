package com.rendezvous.domain.service;


import com.rendezvous.domain.model.*;
import com.rendezvous.domain.repository.*;
import com.rendezvous.dto.clientProfileDto.ClientProfileRequestDTO;
import com.rendezvous.dto.clientProfileDto.ClientProfileResponseDTO;
import com.rendezvous.dto.providerProfileDto.ProviderProfileRequestDTO;
import com.rendezvous.dto.providerProfileDto.ProviderProfileResponseDTO;
import com.rendezvous.exception.ClientInUseException;
import com.rendezvous.exception.ClientNotFoundException;
import com.rendezvous.mapper.ClientProfileMapper;
import com.rendezvous.mapper.ProviderProfileMapper;
import com.rendezvous.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ClientProfileService {

    private UserRepository userRepository;

    private ClientProfileRepository clientProfileRepository;

    private RoleRepository roleRepository;

    private AppointmentRepository appointmentRepository;

    private ClientProfileMapper clientProfileMapper;

    private UserMapper userMapper;

    public ClientProfileService(UserRepository userRepository,
                                ClientProfileRepository clientProfileRepository,
                                RoleRepository roleRepository,
                                AppointmentRepository appointmentRepository,
                                ClientProfileMapper clientProfileMapper,
                                UserMapper userMapper){
        this.userRepository = userRepository;
        this.clientProfileRepository = clientProfileRepository;
        this.roleRepository = roleRepository;
        this.appointmentRepository = appointmentRepository;
        this.clientProfileMapper = clientProfileMapper;
        this.userMapper = userMapper;

    }

    @Transactional
    public List<ClientProfileResponseDTO> findClientAll(){
        List<ClientProfile> clients = clientProfileRepository.findAll();
            return clients.stream()
                    .map(client -> clientProfileMapper.toResponseDTO(client))
                    .toList();
    }

    @Transactional
    public ClientProfileResponseDTO findById(Long clientId){
        ClientProfile client = clientProfileRepository.findById(clientId)
                .orElseThrow(()-> new ClientNotFoundException());
        return clientProfileMapper.toResponseDTO(client);
    }

    @Transactional
    public ClientProfileResponseDTO createClient(ClientProfileRequestDTO clientDTO){
        List<Role> roles = new ArrayList<>(roleRepository.findAllById(clientDTO.getRolesIds()));

        User user = userMapper.toEntity(clientDTO.getEmail(), clientDTO.getPassword(), roles);
        user.setEnable(true); // avaliar se deve ficar aqui
        User userSaved = userRepository.save(user);

        ClientProfile client =  clientProfileMapper.toEntity(clientDTO, userSaved);
        ClientProfile clientSaved = clientProfileRepository.save(client);

       return clientProfileMapper.toResponseDTO(clientSaved);
    }

    @Transactional
    public void deleteClient(Long id){
        ClientProfile client = clientProfileRepository.findById(id)
                .orElseThrow(()-> new ClientNotFoundException());
        boolean hasAppointments  = appointmentRepository.existsByClient(client);
        if (hasAppointments){
            throw new ClientInUseException();
        }
        Optional<User> user = userRepository.findById(client.getUser().getId());
        clientProfileRepository.delete(client);
        userRepository.delete(user.get());
    }
}
