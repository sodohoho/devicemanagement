package com.example.devicemanagement.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.devicemanagement.common.ApiConstant;
import com.example.devicemanagement.entity.Device;
import com.example.devicemanagement.entity.DeviceDetails;
import com.example.devicemanagement.repository.DeviceDetailsRepository;
import com.example.devicemanagement.repository.DeviceRepository;
import com.example.devicemanagement.service.DeviceDetailsService;

@ExtendWith(MockitoExtension.class)
class DeviceDetailsServiceImplTest {

    @InjectMocks
    DeviceServiceImpl deviceService;

    @Mock
    DeviceRepository deviceRepository;

    @Mock
    DeviceDetailsService deviceDetailsService;

    @Mock
    DeviceDetailsRepository deviceDetailsRepository;

    @Test
    void testLogDeviceData() {
        UUID randomUUID = UUID.randomUUID();
        Device device = new Device();
        device.setDeviceId(randomUUID);

        when(deviceRepository.save(any())).thenReturn(device);
        when(deviceDetailsService.saveAllDetails(any())).thenReturn(Arrays.asList(new DeviceDetails()));

        assertEquals(randomUUID, deviceService.logDeviceData(device).getDeviceId());
    }

    @Test
    void testGetDeviceData() {
        UUID randomUUID = UUID.randomUUID();
        Device device = new Device();
        device.setDeviceId(randomUUID);
        LocalDateTime now = LocalDateTime.now();

        when(deviceRepository.findById(any())).thenReturn(Optional.of(device));
        when(deviceDetailsRepository.findByDeviceIdAndTimestampBetween(randomUUID, now, now))
                .thenReturn(Arrays.asList(new DeviceDetails()));

        assertEquals(randomUUID, deviceService.getDeviceData(randomUUID, now, now).getDeviceId());
    }

    @Test
    void testGetDeviceData_StartTimeNull() {
        UUID randomUUID = UUID.randomUUID();
        Device device = new Device();
        device.setDeviceId(randomUUID);
        LocalDateTime now = LocalDateTime.now();

        when(deviceRepository.findById(any())).thenReturn(Optional.of(device));
        when(deviceDetailsRepository.findByDeviceIdAndTimestampBetween(randomUUID, ApiConstant.MIN_DATE, now))
                .thenReturn(Arrays.asList(new DeviceDetails()));

        assertEquals(randomUUID, deviceService.getDeviceData(randomUUID, null, now).getDeviceId());
    }

    @Test
    void testGetDeviceData_EndTimeNull() {
        UUID randomUUID = UUID.randomUUID();
        Device device = new Device();
        device.setDeviceId(randomUUID);
        LocalDateTime now = LocalDateTime.now();

        when(deviceRepository.findById(any())).thenReturn(Optional.of(device));
        when(deviceDetailsRepository.findByDeviceIdAndTimestampBetween(randomUUID, now, ApiConstant.MAX_DATE))
                .thenReturn(Arrays.asList(new DeviceDetails()));

        assertEquals(randomUUID, deviceService.getDeviceData(randomUUID, now, null).getDeviceId());
    }

    @Test
    void testGetDeviceData_StartEndTimeNull() {
        UUID randomUUID = UUID.randomUUID();
        Device device = new Device();
        device.setDeviceId(randomUUID);

        when(deviceRepository.findById(any())).thenReturn(Optional.of(device));
        when(deviceDetailsRepository.findByDeviceIdAndTimestampBetween(randomUUID, ApiConstant.MIN_DATE,
                ApiConstant.MAX_DATE)).thenReturn(Arrays.asList(new DeviceDetails()));

        assertEquals(randomUUID, deviceService.getDeviceData(randomUUID, null, null).getDeviceId());
    }

}
