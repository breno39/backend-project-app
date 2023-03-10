package com.breno39.backendproject.service.application.service;

import com.breno39.backendproject.service.domain.ServiceType;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public interface ServiceService {
    ServiceType[] getAvailableServices();

    com.breno39.backendproject.service.domain.Service addAvailableServiceToDevice(UUID ServiceTypeId, UUID deviceId);

    void removeAvailableServiceFromDevice(UUID ServiceTypeId, UUID deviceId);
}
