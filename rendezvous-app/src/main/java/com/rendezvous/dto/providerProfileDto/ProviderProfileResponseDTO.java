package com.rendezvous.dto.providerProfileDto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public class ProviderProfileResponseDTO {

   @NotNull
   private Long id;
   @NotBlank
   private String companyName;
   @NotBlank
   private String phone;
   @NotBlank
   private String description;
   @NotEmpty
   private List<Long> roles;

   public ProviderProfileResponseDTO(Long id, String companyName, String phone, String description, String email, List<Long> roles){
       this.id = id;
       this.companyName = companyName;
       this.phone = phone;
       this.description = description;
       this.roles = roles;
   }

    public ProviderProfileResponseDTO(Long id, String companyName, String phone, String description){
        this.id = id;
        this.companyName = companyName;
        this.phone = phone;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
