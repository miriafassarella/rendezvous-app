package com.rendezvous.controller;

import com.rendezvous.domain.service.AdminProfileService;
import com.rendezvous.domain.service.ClientProfileService;
import com.rendezvous.dto.adminProfileDto.AdminProfileRequestDTO;
import com.rendezvous.dto.adminProfileDto.AdminProfileResponseDTO;
import com.rendezvous.dto.clientProfileDto.ClientProfileRequestDTO;
import com.rendezvous.dto.clientProfileDto.ClientProfileResponseDTO;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admins")
public class AdminController {

    private AdminProfileService adminProfileService;

    public AdminController(AdminProfileService adminProfileService){
        this.adminProfileService = adminProfileService;
    }

    @PostMapping("/create")
    @PreAuthorize("hasAuthority('')")
    public ResponseEntity<AdminProfileResponseDTO> createAdmin(@Valid @RequestBody AdminProfileRequestDTO adminDTO){
        AdminProfileResponseDTO newAdmin = adminProfileService.createAdmin(adminDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(newAdmin);
    }
}
