package com.rendezvous.mapper;

import com.rendezvous.domain.model.Role;
import com.rendezvous.domain.model.User;
import com.rendezvous.dto.userDto.UserResponseDTO;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserMapper {

    public User toEntity(String email, String password, List<Role> roles) {
        User user = new User();
        user.setEmail(email);
        user.setPassword(password);
        user.setRoles(roles);
        return user;
    }

    public UserResponseDTO userResponseDTO(User user){
        return new UserResponseDTO(
                user.getId(),
                user.getEmail(),
                user.isEnable(),
                user.getCreatedAt(),
                user.getRoles());
    }
}
