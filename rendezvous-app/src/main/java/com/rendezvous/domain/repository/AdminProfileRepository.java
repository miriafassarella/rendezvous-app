package com.rendezvous.domain.repository;

import com.rendezvous.domain.model.AdminProfile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminProfileRepository extends JpaRepository<AdminProfile, Long> {
}
