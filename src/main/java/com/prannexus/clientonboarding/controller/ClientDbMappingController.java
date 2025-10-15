package com.prannexus.clientonboarding.controller;

import com.prannexus.clientonboarding.entity.ClientDbMappingEntity;
import com.prannexus.clientonboarding.repository.ClientDbMappingRepository;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/client-db-mapping")
public class ClientDbMappingController {

    private final ClientDbMappingRepository repository;

    public ClientDbMappingController(ClientDbMappingRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<ClientDbMappingEntity> getAllMappings() {
        return repository.findAll();
    }

    @GetMapping("/{clientCode}")
    public ClientDbMappingEntity getMappingByClientCode(@PathVariable String clientCode) {
        return repository.findById(clientCode)
                .orElseThrow(() -> new RuntimeException("Mapping not found for: " + clientCode));
    }
}
