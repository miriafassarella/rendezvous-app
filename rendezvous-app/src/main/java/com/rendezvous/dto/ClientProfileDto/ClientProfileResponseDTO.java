package com.rendezvous.dto.ClientProfileDto;

import com.rendezvous.domain.model.User;

import java.util.List;
import java.util.Optional;

public class ClientProfileResponseDTO {

    private Long id;
    private String name;

    private String phone;
    private String email;

    //private List<Long> rolesIds;
    private User user;

    public ClientProfileResponseDTO(Long id, String name, String phone, String email){
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.email = email;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}