package com.rendezvous.domain.repository;

import com.rendezvous.domain.model.Appointment;
import com.rendezvous.domain.model.ClientProfile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ClientProfileRepository extends JpaRepository<ClientProfile, Long> {
    /*Método usado no appointemntService para pesquisar os appointemnts de um cliente*/
    Optional<ClientProfile> findByUserId(Long userId);

}
