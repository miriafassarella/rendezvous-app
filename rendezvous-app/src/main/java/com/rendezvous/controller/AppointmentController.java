package com.rendezvous.controller;

import com.rendezvous.domain.model.Appointment;
import com.rendezvous.domain.repository.AppointmentRepository;
import com.rendezvous.domain.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/appointments")
public class AppointmentController {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private AppointmentService appointmentService;

    @GetMapping
    public List<Appointment> listAppointments(){
        return appointmentRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Appointment> searchId(@PathVariable Long id){
        Optional<Appointment> appointment = appointmentRepository.findById(id);

        return ResponseEntity.status(HttpStatus.OK).body(appointment.get());
    }

    @PostMapping
    public ResponseEntity<Appointment> addAppointment(Appointment appointment){
        Appointment appointmentSave = appointmentService.addAppointment(appointment);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Appointment> updateAppointment(@PathVariable Long id, @RequestBody Appointment appointment){
        Appointment appointmentModified = appointmentService.updateAppointment(id, appointment);
        return ResponseEntity.status(HttpStatus.OK).body(appointmentModified);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Appointment> removeAppointment(@PathVariable Long id){
        appointmentService.removeAppointment(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
