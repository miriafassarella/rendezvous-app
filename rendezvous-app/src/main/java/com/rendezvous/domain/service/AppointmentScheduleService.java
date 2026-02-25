package com.rendezvous.domain.service;

import com.rendezvous.domain.enums.Status;
import com.rendezvous.domain.model.Appointment;
import com.rendezvous.domain.repository.AppointmentRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AppointmentScheduleService {

    private AppointmentRepository appointmentRepository;

    public AppointmentScheduleService(AppointmentRepository appointmentRepository){
        this.appointmentRepository = appointmentRepository;
    }

    @Scheduled(fixedDelay = 300000)
    public void autoCompleteAppointments() {
        List<Appointment> appointments = appointmentRepository.findAppointmentsToComplete(LocalDateTime.now());
        appointments.stream()
                .forEach(a-> a.completed());
    }
}
