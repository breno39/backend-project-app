package com.ninjaone.backendinterviewproject.service.application.service;

import com.ninjaone.backendinterviewproject.device.application.service.DeviceService;
import com.ninjaone.backendinterviewproject.device.domain.Device;
import com.ninjaone.backendinterviewproject.handler.ApiException;
import com.ninjaone.backendinterviewproject.service.application.repository.ServiceRepository;
import com.ninjaone.backendinterviewproject.service.domain.ServiceType;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ServiceSpringDataJPAService implements ServiceService{
    private Logger logger = LoggerFactory.getLogger(ServiceSpringDataJPAService.class);

    private final ServiceRepository serviceRepository;

    private final DeviceService deviceService;

    @Override
    public Set<ServiceType> getAvailableServices() {
        logger.info("[START] - ServiceSpringDataJPAService - getAvailableServices");
        var availableServiceTypes = Arrays.stream(ServiceType.values());
        logger.info("[FINISH] - ServiceSpringDataJPAService - getAvailableServices");
        return availableServiceTypes.collect(Collectors.toSet());
    }

    @Override
    public void addAvailableServiceToDevice(ServiceType type, UUID deviceId, UUID customerId) {
        logger.info("[START] - ServiceSpringDataJPAService - addAvailableServiceToDevice");
        Device returnedDevice = deviceService.getDeviceById(deviceId, customerId);
        if(!verifyServiceTypeExistsInDevice(type, returnedDevice)) {
            var createdService= serviceRepository.createService(new com.ninjaone.backendinterviewproject.service.domain.Service(type));
            returnedDevice.addService(createdService);
            deviceService.updateDevice(returnedDevice, deviceId, customerId);
        } else {
            throw ApiException.throwApiException(HttpStatus.BAD_REQUEST, "Service already exists in Device");
        }
        logger.info("[FINISH] - ServiceSpringDataJPAService - addAvailableServiceToDevice");
    }

    @Override
    public void removeAvailableServiceFromDevice(ServiceType type, UUID deviceId, UUID customerId) {
        logger.info("[START] - ServiceSpringDataJPAService - removeAvailableServiceFromDevice");
        Device returnedDevice = deviceService.getDeviceById(deviceId, customerId);
        if(verifyServiceTypeExistsInDevice(type, returnedDevice)) {
            returnedDevice.RemoveService(type);
            deviceService.updateDevice(returnedDevice, deviceId, customerId);
        } else {
            throw ApiException.throwApiException(HttpStatus.BAD_REQUEST, "Service do not exists in Device");
        }
        logger.info("[FINISH] - ServiceSpringDataJPAService - removeAvailableServiceFromDevice");
    }

    private boolean verifyServiceTypeExistsInDevice(ServiceType type, Device returnedDevice) {
        return returnedDevice.getServices().stream().anyMatch(service -> service.isType(type));
    }
}
