package com.rendezvous.controller;

import com.rendezvous.domain.model.Role;
import com.rendezvous.domain.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/roles")
public class RoleController {

    @Autowired
    private RoleRepository roleRepository;

    @GetMapping
    public List<Role> listRoles(){
        return roleRepository.findAll();
    }

    public ResponseEntity<Role> searchId(@PathVariable Long id){
        Optional<Role> role = roleRepository.findById(id);
        return ResponseEntity.ok().body(role.get());
    }

}
