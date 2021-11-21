package com.example.devicemanagement.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.devicemanagement.entity.DeviceDetails;
import com.example.devicemanagement.repository.DeviceDetailsRepository;

@ExtendWith(MockitoExtension.class)
class DeviceServiceImplTest {

    @InjectMocks
    DeviceDetailsServiceImpl deviceDetailsService;

    @Mock
    DeviceDetailsRepository deviceDetailsRepository;

    @Test
    void testLogDeviceData() {
        UUID randomUUID = UUID.randomUUID();
        List<DeviceDetails> lstDetails = new ArrayList<>();
        DeviceDetails details = new DeviceDetails();
        details.setDeviceId(randomUUID);
        details.setHumidity(1);
        lstDetails.add(details);
        details.setHumidity(2);
        lstDetails.add(details);

        when(deviceDetailsRepository.saveAll(lstDetails)).thenReturn(lstDetails);

        assertEquals(2, deviceDetailsService.saveAllDetails(lstDetails).size());
    }

}
