package com.rendezvous.controller;


import com.rendezvous.domain.model.User;
import com.rendezvous.domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    UserRepository userRepository;

    public UserController(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @GetMapping
    public List<User> listUsers(){
        return userRepository.findAll();
    }

    @GetMapping("/{id}")
    public User searchId(@PathVariable Long id){
        Optional<User> userId = userRepository.findById(id);
        return userId.get();
    }


}
