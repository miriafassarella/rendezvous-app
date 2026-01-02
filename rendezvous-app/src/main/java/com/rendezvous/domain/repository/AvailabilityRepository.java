package com.rendezvous.domain.repository;

import com.rendezvous.domain.model.Availability;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AvailabilityRepository extends JpaRepository<Availability, Long> {

}
