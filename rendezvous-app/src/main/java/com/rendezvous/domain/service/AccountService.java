package com.rendezvous.domain.service;


import com.rendezvous.domain.model.ClientProfile;
import com.rendezvous.domain.model.ProviderProfile;
import com.rendezvous.domain.model.Role;
import com.rendezvous.domain.model.User;
import com.rendezvous.domain.repository.ClientProfileRepository;
import com.rendezvous.domain.repository.ProviderProfileRepositoy;
import com.rendezvous.domain.repository.RoleRepository;
import com.rendezvous.domain.repository.UserRepository;
import com.rendezvous.dto.ClientProfileDto.ClientProfileRequestDTO;
import com.rendezvous.dto.ClientProfileDto.ClientProfileResponseDTO;
import com.rendezvous.dto.ProviderProfileDto.ProviderProfileRequestDTO;
import com.rendezvous.dto.ProviderProfileDto.ProviderProfileResponseDTO;
import com.rendezvous.dto.ServiceDto.ServiseResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AccountService {


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ClientProfileRepository clientProfileRepository;

    @Autowired
    private ProviderProfileRepositoy providerProfileRepository;

    @Autowired
    RoleRepository roleRepository;

    public List<ClientProfileResponseDTO> findClientAll(){

       return clientProfileRepository.findAll()
               .stream()
               .map(client-> {
                   ClientProfileResponseDTO dto = new ClientProfileResponseDTO(client.getId(), client.getFirstName() + " " + client.getLastName(),
                           client.getPhone(), client.getUser().getEmail());

                   return dto;
               })
               .toList();
    }

    public ClientProfileResponseDTO createClient(ClientProfileRequestDTO clientDTO){

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

       ClientProfile clientSaved =  clientProfileRepository.save(client);

       return new ClientProfileResponseDTO(
               clientSaved.getId(),
               clientSaved.getFirstName() + " " + clientSaved.getLastName(),
               clientSaved.getPhone(),
               userSave.getEmail()

       );
    }



    public void deleteClient(Long id){
        Optional<ClientProfile> client = clientProfileRepository.findById(id);
        if(client.isEmpty()){
            //TODO
        }else {
            clientProfileRepository.delete(client.get());
        }

    }

    public ProviderProfileResponseDTO createProvide(ProviderProfileRequestDTO providerDTO){
        User user = new User();
        user.setEmail(providerDTO.getEmail());
        user.setPassword(providerDTO.getPassword());
        user.setEnable(providerDTO.isEnable());

        List<Role> roles = new ArrayList<>(roleRepository.findAllById(providerDTO.getRolesIds()));
        user.setRole(roles);

        User userSave =  userRepository.save(user);

        ProviderProfile provider = new ProviderProfile();
        provider.setCompanyName(providerDTO.getCompanyName());
        provider.setPhone(providerDTO.getPhone());
        provider.setDescription(providerDTO.getDescription());
        provider.setUser(userSave);


        ProviderProfile providerSaved = providerProfileRepository.save(provider);

        return new ProviderProfileResponseDTO(
                providerSaved.getId(),
                providerSaved.getCompanyName(),
                providerSaved.getPhone(),
                providerSaved.getDescription(),
                userSave.getEmail()
        );
    }

    public void deleteProvider(Long id){
        Optional<ProviderProfile> provider = providerProfileRepository.findById(id);
        if(provider.isEmpty()){
            //TODO
        }else {
            providerProfileRepository.delete(provider.get());
        }

    }

}
