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
import com.rendezvous.mapper.ClientProfileMapper;
import com.rendezvous.mapper.UserMapper;
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
    private RoleRepository roleRepository;

    @Autowired
    private ClientProfileMapper clientProfileMapper;

    @Autowired
    private UserMapper userMapper;

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
        List<Role> roles = new ArrayList<>(roleRepository.findAllById(clientDTO.getRolesIds()));

        User user = userMapper.toEntity(clientDTO.getEmail(), clientDTO.getPassword(), roles);
        User userSaved = userRepository.save(user);

        ClientProfile client =  clientProfileMapper.toEntity(clientDTO, userSaved);
        ClientProfile savedUser = clientProfileRepository.save(client);

       return clientProfileMapper.toResponseDTO(savedUser);
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
        user.setRoles(roles);

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
