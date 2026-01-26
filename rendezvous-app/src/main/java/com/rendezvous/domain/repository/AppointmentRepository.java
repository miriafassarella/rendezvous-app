package com.rendezvous.domain.repository;

import com.rendezvous.domain.model.Appointment;
import com.rendezvous.domain.model.ProviderProfile;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("""
    SELECT a FROM Appointment a
    WHERE a.provider = :provider
      AND a.day_of_week = :ay_of_week
      AND a.startTime < :endTime
      AND a.endTime > :startTime
""")
    List<Appointment> findConflictingAppointmentsForLock(
            @Param("provider") ProviderProfile provider,
            @Param("ay_of_week") DayOfWeek ay_of_week,
            @Param("startTime") LocalTime startTime,
            @Param("endTime") LocalTime endTime
    );

}
