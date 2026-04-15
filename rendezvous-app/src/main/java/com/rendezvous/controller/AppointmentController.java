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

    @PreAuthorize("hasAuthority('SEARCH_APPOINTMENT')")
    @GetMapping
    public List<AppointmentResponseDTO> findAppointmentsAll(){
        return appointmentService.findAppointmentsAll();
    }

    @PreAuthorize("hasAuthority('CREATE_APPOINTMENT')")
    @PostMapping
    public ResponseEntity<AppointmentResponseDTO> createAppointment(@Valid @RequestBody AppointmentRequestDTO appointmentDTO){
        AppointmentResponseDTO appointmentSaved = appointmentService.createAppointment(appointmentDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(appointmentSaved);
    }

    @PreAuthorize("hasAuthority('UPDATE_APPOINTMENT')")
    @PutMapping("/{appointmentId}")
    public ResponseEntity<AppointmentResponseDTO> modifyAppointment(@RequestBody AppointmentRequestDTO appointmentDTO, @PathVariable Long appointmentId){
        AppointmentResponseDTO appointmentResponseDTO = appointmentService.modifyAppointment(appointmentDTO, appointmentId);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(appointmentResponseDTO);
    }

    @PreAuthorize("hasAuthority('DELETE_APPOINTMENT')")
    @DeleteMapping("/{appointmentId}")
    public ResponseEntity<Appointment> deleteAppointment(@PathVariable Long appointmentId, @AuthenticationPrincipal User loggedUser){
        appointmentService.deleteAppointment(appointmentId, loggedUser);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    /*----------------------a refletir se usar @@AuthenticationPrincipal-------------------------------*/
    //AuthenticationPrincipal pega o usuario no token
    @PreAuthorize("hasAuthority('SEARCH_OWN_APPOINTMENT')")
    @GetMapping("/provider/appointments")
    public ResponseEntity<List<AppointmentResponseDTO>> findByProviderId(@AuthenticationPrincipal User loggedUser){
        List<AppointmentResponseDTO> appointments =  appointmentService.findByProviderId(loggedUser);
        return ResponseEntity.status(HttpStatus.OK).body(appointments);
    }

    @PreAuthorize("hasAuthority('SEARCH_OWN_APPOINTMENT')")
    @GetMapping("/client/appointments")
    public ResponseEntity<List<AppointmentResponseDTO>> findByClientId(@AuthenticationPrincipal User loggedUser){
        List<AppointmentResponseDTO> appointments = appointmentService.findByClientId(loggedUser);
        return ResponseEntity.status(HttpStatus.OK).body(appointments);
    }
/*--------------------------------------------------------------------------------------------------------------*/
    @PreAuthorize("hasAuthority('CANCEL_APPOINTMENT')")
    @PatchMapping("/{appointmentId}/cancel")
    public ResponseEntity<AppointmentResponseDTO> canceledAppointment(@PathVariable Long appointmentId){
        AppointmentResponseDTO appointmentDTO = appointmentService.canceledAppointment(appointmentId);
        return ResponseEntity.status(HttpStatus.OK).body(appointmentDTO);
    }

    @PreAuthorize("hasAuthority('CONFIRM_APPOINTMENT')")
    @PatchMapping("/{appointmentId}/confirm")
    public ResponseEntity<AppointmentResponseDTO> confirmedAppointemnt(@PathVariable Long appointmentId){
       AppointmentResponseDTO appointmentDTO = appointmentService.confirmedAppointment(appointmentId);
        return ResponseEntity.status(HttpStatus.OK).body(appointmentDTO);
    }
}