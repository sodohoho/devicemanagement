package com.example.devicemanagement.service;

import java.time.LocalDateTime;
import java.util.UUID;

import com.example.devicemanagement.entity.Device;

public interface DeviceService {

    Device logDeviceData(Device device);

    Device getDeviceData(UUID deviceId, LocalDateTime startTime, LocalDateTime endTime);

    Device storeDevice(Device device);

}
