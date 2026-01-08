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

    public void deleteClient(Long id){
        Optional<ClientProfile> client = clientProfileRepository.findById(id);
        if(client.isEmpty()){
            //TODO
        }else {
            clientProfileRepository.delete(client.get());
        }

    }

    public ProviderProfile createProvide(ProviderProfileRequestDTO providerDTO){
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
        provider.setUser(userSave);


        return providerProfileRepository.save(provider);

    }

}
