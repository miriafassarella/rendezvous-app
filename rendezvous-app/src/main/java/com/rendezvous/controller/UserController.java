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

    @PostMapping
    public ResponseEntity<User> addUser(@RequestBody User user){
        User userSave = userService.addUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(userSave);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User user){
        User userModified = userService.updateUser(id, user);
        return ResponseEntity.status(HttpStatus.OK).body(userModified);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<User> removeUser(@PathVariable Long id){
        userService.removeUser(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
