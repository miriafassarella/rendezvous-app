package com.rendezvous.domain.repository;

import com.rendezvous.domain.model.ProviderProfile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProviderProfileRepositoy extends JpaRepository<ProviderProfile, Long> {
    Optional<ProviderProfile> findByUserId(Long userId);
}
