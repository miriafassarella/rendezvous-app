package com.rendezvous.dto.userDto;

import com.rendezvous.domain.model.Role;

import java.time.LocalDateTime;
import java.util.List;

public class UserResponseDTO {

    private Long id;
    private String email;
    private boolean enable;
    private LocalDateTime createdAt;
    private List<Role> roles;

    public UserResponseDTO(Long id, String email, boolean enable, LocalDateTime createdAt, List<Role> roles){

        this.id = id;
        this.email = email;
        this.enable = enable;
        this.createdAt = createdAt;
        this.roles = roles;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
}
