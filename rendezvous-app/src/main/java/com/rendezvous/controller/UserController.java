package com.rendezvous.controller;


import com.rendezvous.domain.model.User;
import com.rendezvous.domain.repository.UserRepository;
import com.rendezvous.domain.service.UserService;
import com.rendezvous.dto.userDto.UserResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    UserRepository userRepository;

    UserService userService;

    public UserController(UserRepository userRepository, UserService userService){

        this.userRepository = userRepository;
        this.userService = userService;
    }

    @GetMapping()
    public List<UserResponseDTO> findUserAll(){
        return userService.findUserAll();
    }

    @GetMapping("/{id}")
    public UserResponseDTO searchId(@PathVariable Long id){
        return userService.findById(id);
    }

}
