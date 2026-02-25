package com.rendezvous.domain.repository;

import com.rendezvous.domain.model.Availability;
import com.rendezvous.domain.model.ProviderProfile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

public interface AvailabilityRepository extends JpaRepository<Availability, Long> {

    boolean existsByProviderAndDayOfWeekAndStartTimeLessThanAndEndTimeGreaterThan(
            ProviderProfile provider,
            DayOfWeek dayOfWeek,
            LocalDateTime startTime,
            LocalDateTime endTime
    );

    List<Availability> findByProvider(ProviderProfile providerProfile);
}
