package com.prannexus.clientonboarding.mapper;

import com.prannexus.clientonboarding.dto.ClientDocumentDto;
import com.prannexus.clientonboarding.entity.ClientDocumentEntity;
import com.prannexus.clientonboarding.entity.ClientOnboardingEntity;

public class ClientDocumentMapper {

    public static ClientDocumentDto toDto(ClientDocumentEntity entity) {
        if (entity == null) return null;
        ClientDocumentDto dto = new ClientDocumentDto();
        dto.setDocumentId(entity.getDocumentId());
        dto.setClientId(entity.getClient().getClientId());
        dto.setDocumentName(entity.getDocumentName());
        dto.setDocumentType(entity.getDocumentType());
        dto.setDocumentPath(entity.getDocumentPath());
        dto.setUploadedDate(entity.getUploadedDate());
        dto.setUploadedBy(entity.getUploadedBy());
        return dto;
    }

    public static ClientDocumentEntity toEntity(ClientDocumentDto dto, ClientOnboardingEntity client) {
        if (dto == null || client == null) return null;
        ClientDocumentEntity entity = new ClientDocumentEntity();
        entity.setDocumentId(dto.getDocumentId());
        entity.setClient(client);
        entity.setDocumentName(dto.getDocumentName());
        entity.setDocumentType(dto.getDocumentType());
        entity.setDocumentPath(dto.getDocumentPath());
        entity.setUploadedDate(dto.getUploadedDate());
        entity.setUploadedBy(dto.getUploadedBy());
        return entity;
    }
}
