package com.rendezvous.dto.providerProfileDto;

import java.util.List;

public class ProviderProfileResponseDTO {

   private Long id;
   private String companyName;
   private String phone;
   private String description;
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
