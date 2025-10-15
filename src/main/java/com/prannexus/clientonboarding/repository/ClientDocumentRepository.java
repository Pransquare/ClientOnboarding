package com.prannexus.clientonboarding.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.prannexus.clientonboarding.entity.ClientDocumentEntity;

import java.util.List;

@Repository
public interface ClientDocumentRepository extends JpaRepository<ClientDocumentEntity, Long> {
    List<ClientDocumentEntity> findByClient_ClientId(Long clientId);
}
