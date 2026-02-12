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
public class ProviderProfileService {

    private UserRepository userRepository;

    private ProviderProfileRepositoy providerProfileRepository;

    private RoleRepository roleRepository;

    private ProviderProfileMapper providerProfileMapper;

    private UserMapper userMapper;

    public ProviderProfileService(UserRepository userRepository,
                                  ProviderProfileRepositoy providerProfileRepositoy,
                                  RoleRepository roleRepository,
                                  ProviderProfileMapper providerProfileMapper,
                                  UserMapper userMapper){
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.providerProfileRepository = providerProfileRepositoy;
        this.providerProfileMapper = providerProfileMapper;
        this.userMapper = userMapper;

    }


    @Transactional
    public List<ProviderProfileResponseDTO> findProviderAll(){
        List<ProviderProfile> providers = providerProfileRepository.findAll();

        return providers.stream()
                .map(provider -> providerProfileMapper.toResponseDTO(provider))
                .toList();
    }


    @Transactional
    public ProviderProfileResponseDTO createProvide(ProviderProfileRequestDTO providerDTO){
        List<Role> roles = new ArrayList<>(roleRepository.findAllById(providerDTO.getRolesIds()));

        User user = userMapper.toEntity(providerDTO.getEmail(), providerDTO.getPassword(), roles);
        User userSaved = userRepository.save(user);

        ProviderProfile provider =  providerProfileMapper.toEntity(providerDTO, userSaved);
        ProviderProfile providerSaved = providerProfileRepository.save(provider);

        return providerProfileMapper.toResponseDTO(providerSaved);
    }

    @Transactional
    public void deleteProvider(Long id){
        Optional<ProviderProfile> provider = providerProfileRepository.findById(id);
        if(provider.isEmpty()){
            //TODO
        }else {
            providerProfileRepository.delete(provider.get());
        }

    }

}
