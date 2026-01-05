package com.rendezvous.domain.service;


import com.rendezvous.domain.model.ClientProfile;
import com.rendezvous.domain.model.Role;
import com.rendezvous.domain.model.User;
import com.rendezvous.domain.repository.ClientProfileRepository;
import com.rendezvous.domain.repository.RoleRepository;
import com.rendezvous.domain.repository.UserRepository;
import com.rendezvous.dto.ClientProfileRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ClientProfileService {


    @Autowired
    UserRepository userRepository;

    @Autowired
    ClientProfileRepository clientProfileRepository;

    @Autowired
    RoleRepository roleRepository;

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
