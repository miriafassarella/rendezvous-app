package com.rendezvous.domain.repository;

import com.rendezvous.domain.model.TypeOfService;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceRepository extends JpaRepository<TypeOfService, Long> {
}
