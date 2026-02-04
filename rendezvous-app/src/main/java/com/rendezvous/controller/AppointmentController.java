package com.rendezvous.controller;

import com.rendezvous.domain.repository.AppointmentRepository;
import com.rendezvous.domain.service.AppointmentService;
import com.rendezvous.dto.appointmentDto.AppointmentRequestDTO;
import com.rendezvous.dto.appointmentDto.AppointmentResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/appointments")
public class AppointmentController {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private AppointmentService appointmentService;

    @GetMapping
    public List<AppointmentResponseDTO> findAppointmentsAll(){
        return appointmentService.findAppointmentsAll();
    }

    @PostMapping
    public ResponseEntity<AppointmentResponseDTO> createAppointment(@RequestBody AppointmentRequestDTO appointmentDTO){

        AppointmentResponseDTO appointmentSaved = appointmentService.createAppointment(appointmentDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(appointmentSaved);

    }

}