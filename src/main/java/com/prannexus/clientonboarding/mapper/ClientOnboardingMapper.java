package com.prannexus.clientonboarding.mapper;

import com.prannexus.clientonboarding.dto.ClientOnboardingDto;
import com.prannexus.clientonboarding.entity.ClientOnboardingEntity;

import java.util.ArrayList;
import java.util.List;

public class ClientOnboardingMapper {

    public static ClientOnboardingDto toDto(ClientOnboardingEntity entity) {
        if (entity == null) return null;

        ClientOnboardingDto dto = new ClientOnboardingDto();
        dto.setClientId(entity.getClientId());
        dto.setClientName(entity.getClientName());
        dto.setClientCode(entity.getClientCode());
        dto.setEmail(entity.getEmail());
        dto.setContactNumber(entity.getContactNumber());
        dto.setDomain(entity.getDomain());
        dto.setApprovalStatus(entity.getApprovalStatus().name());
        dto.setApprovedBy(entity.getApprovedBy());
        dto.setCreatedDate(entity.getCreatedDate());
        dto.setModifiedDate(entity.getModifiedDate());
        dto.setRemarks(entity.getRemarks());
        return dto;
    }

    public static ClientOnboardingEntity toEntity(ClientOnboardingDto dto) {
        if (dto == null) return null;

        ClientOnboardingEntity entity = new ClientOnboardingEntity();
        entity.setClientId(dto.getClientId());
        entity.setClientName(dto.getClientName());
        entity.setClientCode(dto.getClientCode());
        entity.setEmail(dto.getEmail());
        entity.setContactNumber(dto.getContactNumber());
        entity.setDomain(dto.getDomain());
        if (dto.getApprovalStatus() != null) {
            entity.setApprovalStatus(ClientOnboardingEntity.ApprovalStatus.valueOf(dto.getApprovalStatus()));
        }
        entity.setApprovedBy(dto.getApprovedBy());
        entity.setCreatedDate(dto.getCreatedDate());
        entity.setModifiedDate(dto.getModifiedDate());
        entity.setRemarks(dto.getRemarks());
        return entity;
    }

    public static List<ClientOnboardingDto> toDtoList(List<ClientOnboardingEntity> entities) {
        List<ClientOnboardingDto> list = new ArrayList<>();
        if (entities != null) {
            for (ClientOnboardingEntity entity : entities) {
                list.add(toDto(entity));
            }
        }
        return list;
    }
}
