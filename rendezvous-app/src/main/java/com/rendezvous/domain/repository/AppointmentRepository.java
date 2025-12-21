package com.rendezvous.domain.repository;

import com.rendezvous.domain.model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
}
