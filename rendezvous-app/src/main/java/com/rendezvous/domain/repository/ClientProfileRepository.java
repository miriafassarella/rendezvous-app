package com.rendezvous.domain.repository;

import com.rendezvous.domain.model.Appointment;
import com.rendezvous.domain.model.ClientProfile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClientProfileRepository extends JpaRepository<ClientProfile, Long> {


}
