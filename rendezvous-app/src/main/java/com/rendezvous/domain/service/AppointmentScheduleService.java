package com.rendezvous.domain.service;

import com.rendezvous.domain.enums.Status;
import com.rendezvous.domain.model.Appointment;
import com.rendezvous.domain.repository.AppointmentRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;

@Service
public class AppointmentScheduleService {

    private AppointmentRepository appointmentRepository;

    public AppointmentScheduleService(AppointmentRepository appointmentRepository){
        this.appointmentRepository = appointmentRepository;
    }

    @Scheduled(fixedDelay = 300000)
    @Transactional
    public void autoCompleteAppointments() {
        List<Appointment> appointments = appointmentRepository.findAppointmentsToComplete(
                LocalDateTime.now(ZoneOffset.UTC));
        appointments.forEach(Appointment::completed);
    }
}
