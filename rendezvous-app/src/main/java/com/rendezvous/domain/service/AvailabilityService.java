package com.rendezvous.domain.service;

import com.rendezvous.domain.model.Availability;
import com.rendezvous.domain.repository.AvailabilityRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AvailabilityService {

    @Autowired
    AvailabilityRepository availabilityRepository;

    public Availability addAvailability(Availability availability){
        return availabilityRepository.save(availability);
    }

    public Availability updateAvailability(Long id, Availability availability){
        Optional<Availability> availabilityCurrent = availabilityRepository.findById(id);
        BeanUtils.copyProperties(availability, availabilityCurrent.get(), "id");
       return  availabilityRepository.save(availabilityCurrent.get());
    }

    public void removeAvailability(Long id){
        Optional<Availability> availability = availabilityRepository.findById(id);
        if(availability.isEmpty()){
            //TODO
        }
        availabilityRepository.delete(availability.get());
    }
}
