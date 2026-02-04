package com.rendezvous.mapper;

import com.rendezvous.domain.model.ProviderProfile;
import com.rendezvous.domain.model.ProviderService;
import com.rendezvous.dto.providerServiceDto.ProviderServiceRequestDTO;
import com.rendezvous.dto.providerServiceDto.ProviderServiseResponseDTO;
import org.springframework.stereotype.Component;

@Component
public class ProviderServiceMapper {

    public ProviderService toEntity(ProviderServiceRequestDTO serviceDTO, ProviderProfile provider){
        ProviderService service = new ProviderService();
        service.setName(serviceDTO.getName());
        service.setDescription(serviceDTO.getDescription());
        service.setPrice(serviceDTO.getPrice());
        service.setDuration_minutes(serviceDTO.getDurationMinutes());
        service.setBuffer_minutes(serviceDTO.getBufferMinutes());
        service.setProvider(provider);

        return service;
    }

    public ProviderServiseResponseDTO toResponseDTO(ProviderService service){
        return new ProviderServiseResponseDTO(
                service.getId(),
                service.getName(),
                service.getDescription(),
                service.getDuration_minutes(),
                service.getBuffer_minutes(),
                service.getPrice(),
                service.getProvider().getId(),
                service.isActive()
        );
    }
}
