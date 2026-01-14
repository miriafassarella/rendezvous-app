package com.rendezvous.mapper;

import com.rendezvous.domain.model.ClientProfile;
import com.rendezvous.domain.model.Role;
import com.rendezvous.domain.model.User;
import com.rendezvous.dto.ClientProfileDto.ClientProfileRequestDTO;
import com.rendezvous.dto.ClientProfileDto.ClientProfileResponseDTO;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ClientProfileMapper {

    public ClientProfile toEntity(ClientProfileRequestDTO clientDTO, User user){

        ClientProfile client = new ClientProfile();
        client.setFirstName(clientDTO.getFirstName());
        client.setLastName(clientDTO.getLastName());
        client.setPhone(clientDTO.getPhone());
        client.setUser(user);

        return client;
    }

    public ClientProfileResponseDTO toResponseDTO(ClientProfile client){
        return new ClientProfileResponseDTO(
                client.getId(),
                client.getFirstName() + " " + client.getLastName(),
                client.getPhone(),
                client.getUser().getEmail()
        );
    }
}
