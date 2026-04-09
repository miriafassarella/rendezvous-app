package com.rendezvous.mapper;

import com.rendezvous.domain.model.AdminProfile;
import com.rendezvous.domain.model.User;
import com.rendezvous.dto.adminProfileDto.AdminProfileRequestDTO;
import com.rendezvous.dto.adminProfileDto.AdminProfileResponseDTO;
import org.springframework.stereotype.Component;

@Component
public class AdminProfileMapper {

    public AdminProfile toEntity(AdminProfileRequestDTO adminDTO, User user){
        AdminProfile admin = new AdminProfile();
        admin.setFirstName(adminDTO.getFirstName());
        admin.setLastName(adminDTO.getLastName());
        admin.setPhone(adminDTO.getPhone());
        admin.setUser(user);

        return admin;
    }

    public AdminProfileResponseDTO toResponseDTO(AdminProfile admin){
        return new AdminProfileResponseDTO(
                admin.getId(),
                admin.getFirstName() + " " + admin.getLastName(),
                admin.getPhone(),
                admin.getUser().getEmail()
        );
    }
}
