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
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admins")
public class AdminController {

    private AdminProfileService adminProfileService;

    public AdminController(AdminProfileService adminProfileService){
        this.adminProfileService = adminProfileService;
    }

    @PreAuthorize("hasAuthority('SEARCH_ADMIN_PROFILE')")
    @GetMapping
    public ResponseEntity<List<AdminProfileResponseDTO>> findAdminAll(){
        List<AdminProfileResponseDTO> admins = adminProfileService.findAdminAll();
        return ResponseEntity.status(HttpStatus.OK).body(admins);
    }

    @PostMapping("/create")
    @PreAuthorize("hasAuthority('CREATE_ADMIN_PROFILE')")
    public ResponseEntity<AdminProfileResponseDTO> createAdmin(@Valid @RequestBody AdminProfileRequestDTO adminDTO){
        AdminProfileResponseDTO newAdmin = adminProfileService.createAdmin(adminDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(newAdmin);
    }

    @PreAuthorize("hasAuthority('DELETE_ADMIN_PROFILE')")
    @DeleteMapping("/{adminId}")
    public ResponseEntity<?> deleteAdmin(@PathVariable Long adminId){
        adminProfileService.deleteAdmin(adminId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
