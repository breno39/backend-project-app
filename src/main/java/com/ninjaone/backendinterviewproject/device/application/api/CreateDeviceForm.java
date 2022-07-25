package com.ninjaone.backendinterviewproject.device.application.api;

import com.ninjaone.backendinterviewproject.device.domain.Device;
import com.ninjaone.backendinterviewproject.service.application.api.ServiceForm;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.Set;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class CreateDeviceForm extends DeviceForm{
    private Set<ServiceForm> services;

    @Override
    public Device toEntity() {
        Device returnedDevice = super.toEntity();
        returnedDevice.setServices(services.stream().map(ServiceForm::toEntity).collect(Collectors.toUnmodifiableSet()));
        return returnedDevice;
    }
}