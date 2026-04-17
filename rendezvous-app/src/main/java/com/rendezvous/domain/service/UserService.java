package com.rendezvous.domain.service;

import com.rendezvous.domain.model.User;
import com.rendezvous.domain.repository.UserRepository;
import com.rendezvous.dto.userDto.UserResponseDTO;
import com.rendezvous.exception.UserNotFoundException;
import com.rendezvous.mapper.UserMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService {

    private UserRepository userRepository;

    private UserMapper userMapper;

    public UserService(UserRepository userRepository, UserMapper userMapper){
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Transactional
    public List<UserResponseDTO> findUserAll(){
        List<User> users = userRepository.findAll();

        return users.stream()
                .map(user-> userMapper.userResponseDTO(user))
                .toList();
    }

    public UserResponseDTO findById(Long userId){
      User user = userRepository.findById(userId)
              .orElseThrow(()-> new UserNotFoundException());

      return new UserResponseDTO(user.getId(),
              user.getEmail(),
              user.isEnable(),
              user.getCreatedAt(),
              user.getRoles());
    }
}
