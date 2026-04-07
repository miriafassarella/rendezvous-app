package com.rendezvous.controller;

import com.rendezvous.domain.model.Appointment;
import com.rendezvous.domain.model.User;
import com.rendezvous.domain.repository.AppointmentRepository;
import com.rendezvous.domain.service.AppointmentService;
import com.rendezvous.dto.appointmentDto.AppointmentRequestDTO;
import com.rendezvous.dto.appointmentDto.AppointmentResponseDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
    @PreAuthorize("hasAuthority('SEARCH_APPOINTMENT')")
    public List<AppointmentResponseDTO> findAppointmentsAll(){
        return appointmentService.findAppointmentsAll();
    }

    @PostMapping
    @PreAuthorize("hasAuthority('CREATE_APPOINTMENT')")
    public ResponseEntity<AppointmentResponseDTO> createAppointment(@Valid @RequestBody AppointmentRequestDTO appointmentDTO){
        AppointmentResponseDTO appointmentSaved = appointmentService.createAppointment(appointmentDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(appointmentSaved);
    }

    @PutMapping("/{appointmentId}")
    @PreAuthorize("hasAuthority('UPDATE_APPOINTMENT')")
    public ResponseEntity<AppointmentResponseDTO> modifyAppointment(@RequestBody AppointmentRequestDTO appointmentDTO, @PathVariable Long appointmentId){
        AppointmentResponseDTO appointmentResponseDTO = appointmentService.modifyAppointment(appointmentDTO, appointmentId);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(appointmentResponseDTO);
    }

    @DeleteMapping("/{appointmentId}")
    @PreAuthorize("hasAuthority('CANCEL_APPOINTMENT')")
    public ResponseEntity<Appointment> deleteAppointment(@PathVariable Long appointmentId){
        appointmentService.deleteAppointment(appointmentId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    //AuthenticationPrincipal pega o usuario no token
    @GetMapping("/provider/{providerId}")
    @PreAuthorize("hasAuthority('SEARCH_OWN_APPOINTMENT')")
    public ResponseEntity<List<AppointmentResponseDTO>> findByProviderId(@PathVariable Long providerId, @AuthenticationPrincipal User loggedUser){
        List<AppointmentResponseDTO> appointments =  appointmentService.findByProviderId(providerId, loggedUser);
        return ResponseEntity.status(HttpStatus.OK).body(appointments);
    }

    @GetMapping("client/{clientId}")
    @PreAuthorize("hasAuthority('SEARCH_OWN_APPOINTMENT')")
    public ResponseEntity<List<AppointmentResponseDTO>> findByClientId(@PathVariable Long clientId, @AuthenticationPrincipal User loggedUser){
        List<AppointmentResponseDTO> appointments = appointmentService.findByClientId(clientId, loggedUser);
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