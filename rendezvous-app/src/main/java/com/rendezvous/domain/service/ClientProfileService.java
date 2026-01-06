package com.rendezvous.domain.service;


import com.rendezvous.domain.model.ClientProfile;
import com.rendezvous.domain.model.Role;
import com.rendezvous.domain.model.User;
import com.rendezvous.domain.repository.ClientProfileRepository;
import com.rendezvous.domain.repository.RoleRepository;
import com.rendezvous.domain.repository.UserRepository;
import com.rendezvous.dto.ClientProfileDto.ClientProfileRequestDTO;
import com.rendezvous.dto.ClientProfileDto.ClientProfileResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ClientProfileService {


    @Autowired
    UserRepository userRepository;

    @Autowired
    ClientProfileRepository clientProfileRepository;

    @Autowired
    RoleRepository roleRepository;

    public List<ClientProfileResponseDTO> findAll(){

       return clientProfileRepository.findAll()
               .stream()
               .map(client-> {
                   ClientProfileResponseDTO dto = new ClientProfileResponseDTO();
                   dto.setId(client.getId());
                   dto.setPhone(client.getPhone());
                   dto.setName(client.getFirstName() + " " + client.getLastName());
                   dto.setUser(client.getUser());
                   if (client.getUser() != null) {
                       dto.getUser().setEmail(client.getUser().getEmail());
                   }
                   return dto;
               })
               .toList();
    }

    public ClientProfile createClient(ClientProfileRequestDTO clientDTO){

        User user = new User();
        user.setEmail(clientDTO.getEmail());
        user.setPassword(clientDTO.getPassword());
        user.setEnable(clientDTO.isEnable());

        List<Role> roles = new ArrayList<>(roleRepository.findAllById(clientDTO.getRolesIds()));
        user.setRole(roles);

        User userSave =  userRepository.save(user);

        ClientProfile client = new ClientProfile();
        client.setFirstName(clientDTO.getFirstName());
        client.setLastName(clientDTO.getLastName());
        client.setPhone(clientDTO.getPhone());
        client.setUser(userSave);

        return clientProfileRepository.save(client);
    }


}
