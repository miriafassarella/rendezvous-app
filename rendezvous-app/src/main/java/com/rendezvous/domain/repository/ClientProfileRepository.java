package com.rendezvous.domain.repository;

import com.rendezvous.domain.model.ClientProfile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientProfileRepository extends JpaRepository<ClientProfile, Long> {
}
