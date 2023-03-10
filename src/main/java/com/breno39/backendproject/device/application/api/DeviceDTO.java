package com.breno39.backendproject.device.application.api;

import com.breno39.backendproject.service.domain.domain.Device;
import com.breno39.backendproject.service.domain.domain.DeviceType;
import lombok.Getter;

@Getter
public class DeviceDTO {
    private final String systemName;

    private final DeviceType type;

    public DeviceDTO(Device device) {
        this.systemName = device.getSystemName();
        this.type = device.getType();
    }

}
