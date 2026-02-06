package com.rendezvous.domain.service;


import com.rendezvous.domain.model.*;
import com.rendezvous.domain.repository.*;
import com.rendezvous.dto.clientProfileDto.ClientProfileRequestDTO;
import com.rendezvous.dto.clientProfileDto.ClientProfileResponseDTO;
import com.rendezvous.dto.providerProfileDto.ProviderProfileRequestDTO;
import com.rendezvous.dto.providerProfileDto.ProviderProfileResponseDTO;
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


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ClientProfileRepository clientProfileRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private ClientProfileMapper clientProfileMapper;

    @Autowired
    private UserMapper userMapper;


    @Transactional
    public List<ClientProfileResponseDTO> findClientAll(){
        List<ClientProfile> clients = clientProfileRepository.findAll();
            return clients.stream()
                    .map(client -> clientProfileMapper.toResponseDTO(client))
                    .toList();
        }
    @Transactional
    public ClientProfileResponseDTO createClient(ClientProfileRequestDTO clientDTO){
        List<Role> roles = new ArrayList<>(roleRepository.findAllById(clientDTO.getRolesIds()));

        User user = userMapper.toEntity(clientDTO.getEmail(), clientDTO.getPassword(), roles);
        User userSaved = userRepository.save(user);

        ClientProfile client =  clientProfileMapper.toEntity(clientDTO, userSaved);
        ClientProfile clientSaved = clientProfileRepository.save(client);

       return clientProfileMapper.toResponseDTO(clientSaved);
    }

    @Transactional
    public void deleteClient(Long id){
        Optional<ClientProfile> client = clientProfileRepository.findById(id);
        if(client.isEmpty()){
            //TODO
        }else {
            clientProfileRepository.delete(client.get());
        }

    }

}
