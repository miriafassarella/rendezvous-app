package com.rendezvous.domain.repository;

import com.rendezvous.domain.model.ProviderService;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProviderServiceRepository extends JpaRepository<ProviderService, Long> {

    List<ProviderService> findByProviderId(Long providerId);
}
