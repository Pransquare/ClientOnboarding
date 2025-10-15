package com.prannexus.clientonboarding.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.prannexus.clientonboarding.entity.ClientOnboardingEntity;

import java.util.Optional;

@Repository
public interface ClientOnboardingRepository extends JpaRepository<ClientOnboardingEntity, Long> {
    Optional<ClientOnboardingEntity> findByEmail(String email);
    Optional<ClientOnboardingEntity> findByClientCode(String clientCode);
}
