package com.example.devicemanagement.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.devicemanagement.entity.DeviceDetails;
import com.example.devicemanagement.repository.DeviceDetailsRepository;
import com.example.devicemanagement.service.DeviceDetailsService;

@Service
public class DeviceDetailsServiceImpl implements DeviceDetailsService {

    @Autowired
    DeviceDetailsRepository deviceDetailsRepository;

    @Override
    public List<DeviceDetails> saveAllDetails(List<DeviceDetails> listDeviceDetails) {
        return deviceDetailsRepository.saveAll(listDeviceDetails);
    }

}
