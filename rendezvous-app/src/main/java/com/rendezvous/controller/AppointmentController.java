package com.rendezvous.controller;

import com.rendezvous.domain.model.Appointment;
import com.rendezvous.domain.repository.AppointmentRepository;
import com.rendezvous.domain.service.AppointmentService;
import com.rendezvous.dto.appointmentDto.AppointmentRequestDTO;
import com.rendezvous.dto.appointmentDto.AppointmentResponseDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.List;

@RestController
@RequestMapping("/appointments")
public class AppointmentController {


    private AppointmentService appointmentService;

    public AppointmentController(AppointmentService appointmentService){
        this.appointmentService = appointmentService;
    }

    @GetMapping
    public List<AppointmentResponseDTO> findAppointmentsAll(){
        return appointmentService.findAppointmentsAll();
    }

    @PostMapping
    public ResponseEntity<AppointmentResponseDTO> createAppointment(@Valid @RequestBody AppointmentRequestDTO appointmentDTO){
        AppointmentResponseDTO appointmentSaved = appointmentService.createAppointment(appointmentDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(appointmentSaved);
    }

    @PutMapping("/{appointmentId}")
    public ResponseEntity<AppointmentResponseDTO> modifyAppointment(@RequestBody AppointmentRequestDTO appointmentDTO, @PathVariable Long appointmentId){
        AppointmentResponseDTO appointmentResponseDTO = appointmentService.modifyAppointment(appointmentDTO, appointmentId);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(appointmentResponseDTO);
    }

    @DeleteMapping("/{appointmentId}")
    public ResponseEntity<Appointment> deleteAppointment(@PathVariable Long appointmentId){
        appointmentService.deleteAppointment(appointmentId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("/provider/{providerId}")
    public ResponseEntity<List<AppointmentResponseDTO>> findByProviderId(@PathVariable Long providerId){
        List<AppointmentResponseDTO> appointments =  appointmentService.findByProviderId(providerId);
        return ResponseEntity.status(HttpStatus.OK).body(appointments);
    }

    @GetMapping("client/{clientId}")
    public ResponseEntity<List<AppointmentResponseDTO>> findByClientId(@PathVariable Long clientId){
        List<AppointmentResponseDTO> appointments = appointmentService.findByClientId(clientId);
        return ResponseEntity.status(HttpStatus.OK).body(appointments);
    }

    @PatchMapping("/{appointmentId}/cancel")
    public ResponseEntity<AppointmentResponseDTO> canceledAppointment(@PathVariable Long appointmentId){
        AppointmentResponseDTO appointmentDTO = appointmentService.canceledAppointment(appointmentId);
        return ResponseEntity.status(HttpStatus.OK).body(appointmentDTO);
    }

    @PatchMapping("/{appointmentId}/confirm")
    public ResponseEntity<AppointmentResponseDTO> confirmedAppointemnt(@PathVariable Long appointmentId){
       AppointmentResponseDTO appointmentDTO = appointmentService.confirmedAppointment(appointmentId);
        return ResponseEntity.status(HttpStatus.OK).body(appointmentDTO);
    }
}