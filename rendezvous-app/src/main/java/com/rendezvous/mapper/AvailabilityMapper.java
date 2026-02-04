package com.rendezvous.mapper;

import com.rendezvous.domain.model.Availability;
import com.rendezvous.domain.model.ProviderProfile;
import com.rendezvous.dto.availabilityDto.AvailabilityRequestDTO;
import com.rendezvous.dto.availabilityDto.AvailabilityResponseDTO;
import org.springframework.stereotype.Component;

@Component
public class AvailabilityMapper {

    public Availability toEntity(AvailabilityRequestDTO availabilityDTO, ProviderProfile provider){
        Availability availability = new Availability();
        availability.setDayOfWeek(availabilityDTO.getDayOfWeek());
        availability.setStartTime(availabilityDTO.getStartTime());
        availability.setEndTime(availabilityDTO.getEndTime());
        availability.setProvider(provider);

        return availability;
    }

    public AvailabilityResponseDTO toResponseDTO(Availability availability){

        AvailabilityResponseDTO responseDTO = new AvailabilityResponseDTO();
        responseDTO.setId(availability.getId());
        responseDTO.setDayOfWeek(availability.getDayOfWeek());
        responseDTO.setStartTime(availability.getStartTime());
        responseDTO.setEndTime(availability.getEndTime());
        responseDTO.setProviderId(availability.getProvider().getId());

        return responseDTO;
    }

}
