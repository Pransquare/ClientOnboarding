package com.prannexus.clientonboarding.repository;

import com.prannexus.clientonboarding.entity.ClientDbMappingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientDbMappingRepository extends JpaRepository<ClientDbMappingEntity, String> {
    // Optional: you can add custom queries if needed
}
