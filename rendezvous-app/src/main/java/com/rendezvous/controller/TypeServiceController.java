package com.rendezvous.controller;

import com.rendezvous.domain.model.TypeService;
import com.rendezvous.domain.repository.TypeServiceRepository;
import com.rendezvous.domain.service.TypeServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/services")
public class TypeServiceController {

    @Autowired
    private TypeServiceRepository typeServiceRepository;

    @Autowired
    private TypeServiceService typeServiceService;

    @GetMapping
    List<TypeService> listServices(){
        return typeServiceRepository.findAll();
    }

    @GetMapping("/{id}")
    ResponseEntity<TypeService> searchId(@PathVariable Long id){
        Optional<TypeService> service = typeServiceRepository.findById(id);
        return ResponseEntity.ok().body(service.get());
    }

    @PostMapping
    public ResponseEntity<TypeService> addService(@RequestBody TypeService service){
        TypeService serviceSave = typeServiceService.addService(service);
        return ResponseEntity.status(HttpStatus.CREATED).body(serviceSave);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TypeService> updateService(@PathVariable Long id, @RequestBody TypeService service){
        TypeService serviceModified = typeServiceService.updateService(id, service);
        return ResponseEntity.ok().body(serviceModified);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<TypeService> removeService(@PathVariable Long id){
        typeServiceService.removeService(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
