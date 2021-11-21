package com.example.devicemanagement.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.devicemanagement.common.ApiConstant;
import com.example.devicemanagement.common.ApiException;
import com.example.devicemanagement.entity.Device;
import com.example.devicemanagement.entity.DeviceDetails;
import com.example.devicemanagement.repository.DeviceDetailsRepository;
import com.example.devicemanagement.repository.DeviceRepository;
import com.example.devicemanagement.service.DeviceDetailsService;
import com.example.devicemanagement.service.DeviceService;

@Service
public class DeviceServiceImpl implements DeviceService {

    @Autowired
    DeviceRepository deviceRepository;

    @Autowired
    DeviceDetailsService deviceDetailsService;

    @Autowired
    DeviceDetailsRepository deviceDetailsRepository;

    @Override
    public Device logDeviceData(Device device) {
        // save new or update device position
        Device savedDevice = this.storeDevice(device);
        // save details
        savedDevice.setData(deviceDetailsService.saveAllDetails(device.getData()));

        return savedDevice;
    }

    @Override
    public Device getDeviceData(UUID deviceId, LocalDateTime startTime, LocalDateTime endTime) {
        Optional<Device> device = deviceRepository.findById(deviceId);
        if (!device.isPresent()) {
            throw new ApiException("No device found.");
        }
        // add max min for default search
        if (startTime == null) {
            startTime = ApiConstant.MIN_DATE;
        }
        if (endTime == null) {
            endTime = ApiConstant.MAX_DATE;
        }

        List<DeviceDetails> details = deviceDetailsRepository.findByDeviceIdAndTimestampBetween(deviceId, startTime,
                endTime);
        // map
        device.get().setData(details);
        return device.get();
    }

    @Override
    public Device storeDevice(Device device) {
        return deviceRepository.save(device);
    }
}
