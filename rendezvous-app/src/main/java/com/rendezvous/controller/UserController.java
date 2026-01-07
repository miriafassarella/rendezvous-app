package com.rendezvous.controller;


import com.rendezvous.domain.model.User;
import com.rendezvous.domain.repository.UserRepository;
import com.rendezvous.domain.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserService userService;

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
