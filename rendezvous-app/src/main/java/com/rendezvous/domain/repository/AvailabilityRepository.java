package com.rendezvous.domain.repository;

import com.rendezvous.domain.model.Availability;
import com.rendezvous.domain.model.ProviderProfile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;

public interface AvailabilityRepository extends JpaRepository<Availability, Long> {

    /*
    existsBy
    Date
    LessThan
    Equal
    Greater
    ThanEqual
    */
    boolean existsByProviderAndDayOfWeekAndStartTimeLessThanEqualAndEndTimeGreaterThanEqual(
            ProviderProfile provider,
            DayOfWeek dayOfWeek,
            LocalTime startTime,
            LocalTime endTime
    );

}
