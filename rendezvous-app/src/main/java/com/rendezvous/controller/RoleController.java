package com.rendezvous.controller;

import com.rendezvous.domain.model.Role;
import com.rendezvous.domain.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/roles")
public class RoleController {

    @Autowired
    RoleRepository roleRepository;

    @GetMapping
    public List<Role> listRoles(){
        return roleRepository.findAll();
    }
}
