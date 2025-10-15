package com.prannexus.clientonboarding.service;

import com.prannexus.clientonboarding.dto.ClientOnboardingDto;
import java.util.List;

public interface ClientOnboardingService {
    ClientOnboardingDto signup(ClientOnboardingDto dto);
    ClientOnboardingDto approveClient(String clientCode, String approvedBy, String remarks);
    List<ClientOnboardingDto> getAllClients();
    ClientOnboardingDto getClientByCode(String clientCode);
}
