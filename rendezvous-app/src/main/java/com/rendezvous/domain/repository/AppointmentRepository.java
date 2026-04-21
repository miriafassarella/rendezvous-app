package com.rendezvous.domain.repository;

import com.rendezvous.domain.enums.Status;
import com.rendezvous.domain.model.Appointment;
import com.rendezvous.domain.model.ClientProfile;
import com.rendezvous.domain.model.ProviderProfile;
import com.rendezvous.domain.model.ProviderService;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.security.Provider;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {


    List<Appointment> findAllByProvider_Id(Long id);
    List<Appointment> findAllByClient_Id(Long id);

    /*Verificando no banco se há conflito de horario e bloqueando a modificação neste intervalo de horario*/
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("""
    SELECT a FROM Appointment a
    WHERE a.provider = :provider
      AND a.startTime < :endTime
      AND a.endTime > :startTime
""") List<Appointment> findConflictingAppointmentsForLock(
            @Param("provider") ProviderProfile provider,
            @Param("startTime") LocalDateTime startTime,
            @Param("endTime") LocalDateTime endTime
    );

    //verifica se um appointment já passou da data e hora para sinaliar que le foi completado.
    @Query("""
            SELECT a FROM Appointment a
            WHERE a.endTime < :currentTime
""") List<Appointment> findAppointmentsToComplete(@Param("currentTime") LocalDateTime currentTime);

    boolean existsByService(ProviderService service);
    boolean existsByProvider(ProviderProfile provider);
    boolean existsByClient(ClientProfile client);


    boolean existsByProviderAndDayOfWeekAndStartTimeAfterAndStatusNotIn(ProviderProfile providerProfile,
                                         DayOfWeek dayOfWeek,
                                         LocalDateTime now,
                                         List<Status> excludedStatuses);
}
