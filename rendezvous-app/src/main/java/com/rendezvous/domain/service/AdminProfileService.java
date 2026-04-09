package com.rendezvous.domain.service;

import com.rendezvous.domain.model.AdminProfile;
import com.rendezvous.domain.model.ClientProfile;
import com.rendezvous.domain.model.Role;
import com.rendezvous.domain.model.User;
import com.rendezvous.domain.repository.AdminProfileRepository;
import com.rendezvous.domain.repository.RoleRepository;
import com.rendezvous.domain.repository.UserRepository;
import com.rendezvous.dto.adminProfileDto.AdminProfileRequestDTO;
import com.rendezvous.dto.adminProfileDto.AdminProfileResponseDTO;
import com.rendezvous.dto.clientProfileDto.ClientProfileRequestDTO;
import com.rendezvous.dto.clientProfileDto.ClientProfileResponseDTO;
import com.rendezvous.mapper.AdminProfileMapper;
import com.rendezvous.mapper.UserMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class AdminProfileService {

    private AdminProfileRepository adminProfileRepository;
    private RoleRepository roleRepository;
    private UserRepository userRepository;
    private AdminProfileMapper adminProfileMapper;
    private UserMapper userMapper;

    public AdminProfileService(AdminProfileRepository adminProfileRepository,
                               RoleRepository roleRepository,
                               AdminProfileMapper adminProfileMapper,
                               UserMapper userMapper,
                               UserRepository userRepository){
        this.adminProfileRepository = adminProfileRepository;
        this.roleRepository = roleRepository;
    }

    @Transactional
    public AdminProfileResponseDTO createAdmin(AdminProfileRequestDTO adminDTO){
        List<Role> roles = new ArrayList<>(roleRepository.findAllById(adminDTO.getRolesIds()));

        User user = userMapper.toEntity(adminDTO.getEmail(), adminDTO.getPassword(), roles);
        user.setEnable(true); // avaliar se deve ficar aqui
        User userSaved = userRepository.save(user);

        AdminProfile admin =  adminProfileMapper.toEntity(adminDTO, userSaved);
        AdminProfile adminSaved = adminProfileRepository.save(admin);

        return adminProfileMapper.toResponseDTO(adminSaved);
    }
}
