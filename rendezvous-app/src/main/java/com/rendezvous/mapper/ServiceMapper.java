package com.rendezvous.mapper;

import com.rendezvous.domain.model.ProviderProfile;
import com.rendezvous.domain.model.TypeOfService;
import com.rendezvous.dto.ServiceDto.ServiceRequestDTO;
import com.rendezvous.dto.ServiceDto.ServiseResponseDTO;
import org.springframework.stereotype.Component;

@Component
public class ServiceMapper {

    public TypeOfService toEntity(ServiceRequestDTO serviceDTO, ProviderProfile provider){
        TypeOfService service = new TypeOfService();
        service.setName(serviceDTO.getName());
        service.setDescription(serviceDTO.getDescription());
        service.setPrice(serviceDTO.getPrice());
        service.setDuration_minutes(serviceDTO.getDurationMinutes());
        service.setBuffer_minutes(serviceDTO.getBufferMinutes());
        service.setProvider(provider);

        return service;
    }

    public ServiseResponseDTO toResponseDTO(TypeOfService service){
        return new ServiseResponseDTO(
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
