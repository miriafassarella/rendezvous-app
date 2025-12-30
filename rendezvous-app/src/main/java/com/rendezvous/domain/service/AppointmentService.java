package com.rendezvous.domain.service;

import com.rendezvous.domain.model.Appointment;
import com.rendezvous.domain.repository.AppointmentRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AppointmentService {

    @Autowired
    AppointmentRepository appointmentRepository;

    public Appointment addAppointment(Appointment appointment){
        return appointmentRepository.save(appointment);
    }

    public Appointment updateAppointment(Long id, Appointment appointment){
        Optional<Appointment> appointmentCurrent = appointmentRepository.findById(id);

        BeanUtils.copyProperties(appointment, appointmentCurrent.get(),"id");
        return appointmentRepository.save(appointmentCurrent.get());
    }

    public void removeAppointment(Long id){
        Optional<Appointment> appointment = appointmentRepository.findById(id);
        if(appointment.isEmpty()){
            //TODO
        }
        appointmentRepository.delete(appointment.get());
    }
}
