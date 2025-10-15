package com.prannexus.clientonboarding.controller;

import com.prannexus.clientonboarding.dto.ClientOnboardingDto;
import com.prannexus.clientonboarding.service.ClientOnboardingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clients")
public class ClientOnboardingController {

    private final ClientOnboardingService service;

    public ClientOnboardingController(ClientOnboardingService service) {
        this.service = service;
    }

    @PostMapping("/signup")
    public ResponseEntity<ClientOnboardingDto> signup(@RequestBody ClientOnboardingDto dto) {
        return ResponseEntity.ok(service.signup(dto));
    }

    @PutMapping("/approve/{clientCode}")
    public ResponseEntity<ClientOnboardingDto> approveClient(
            @PathVariable String clientCode,
            @RequestParam String approvedBy,
            @RequestParam(required = false) String remarks) {
        return ResponseEntity.ok(service.approveClient(clientCode, approvedBy, remarks));
    }

    @GetMapping
    public ResponseEntity<List<ClientOnboardingDto>> getAllClients() {
        return ResponseEntity.ok(service.getAllClients());
    }

    @GetMapping("/{clientCode}")
    public ResponseEntity<ClientOnboardingDto> getClientByCode(@PathVariable String clientCode) {
        return ResponseEntity.ok(service.getClientByCode(clientCode));
    }
}
