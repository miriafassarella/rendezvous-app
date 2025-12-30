package com.rendezvous.domain.service;

import com.rendezvous.domain.model.TypeService;
import com.rendezvous.domain.repository.TypeServiceRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TypeServiceService {

    @Autowired
    TypeServiceRepository typeServiceRepository;

    public TypeService addService(TypeService service){
        return typeServiceRepository.save(service);
    }

    public TypeService updateService(Long id, TypeService service){
        Optional<TypeService> serviceCurrent = typeServiceRepository.findById(id);
        BeanUtils.copyProperties(service, serviceCurrent, "id");

        return typeServiceRepository.save(serviceCurrent.get());
    }

    public void removeService(Long id){
       Optional<TypeService> service = typeServiceRepository.findById(id);
       if(service.isEmpty()){
           //TODO
       }
       typeServiceRepository.delete(service.get());
    }
}
