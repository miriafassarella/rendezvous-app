package com.rendezvous.domain.repository;

import com.rendezvous.domain.model.ProviderService;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceRepository extends JpaRepository<ProviderService, Long> {
}
