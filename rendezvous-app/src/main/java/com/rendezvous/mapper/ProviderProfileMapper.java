package com.rendezvous.mapper;

import com.rendezvous.domain.model.ProviderProfile;
import com.rendezvous.domain.model.User;
import com.rendezvous.dto.ProviderProfileDto.ProviderProfileRequestDTO;
import com.rendezvous.dto.ProviderProfileDto.ProviderProfileResponseDTO;
import org.springframework.stereotype.Component;

@Component
public class ProviderProfileMapper {

    public ProviderProfile toEntity(ProviderProfileRequestDTO providerDTO, User user){

        ProviderProfile provider = new ProviderProfile();
        provider.setCompanyName(providerDTO.getCompanyName());
        provider.setDescription(providerDTO.getDescription());
        provider.setPhone(providerDTO.getPhone());
        provider.setUser(user);

        return provider;
    }

    public ProviderProfileResponseDTO toResponseDTO(ProviderProfile provider){
        ProviderProfileResponseDTO providerDTO = new ProviderProfileResponseDTO(
                provider.getId(),
                provider.getCompanyName(),
                provider.getPhone(),
                provider.getDescription()
        );
        return providerDTO;
    }
}
