package com.example.devicemanagement.controller;

import java.time.LocalDateTime;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.devicemanagement.common.ApiException;
import com.example.devicemanagement.common.BaseResponse;
import com.example.devicemanagement.dto.DeviceRequestDTO;
import com.example.devicemanagement.dto.DeviceResponseDTO;
import com.example.devicemanagement.entity.Device;
import com.example.devicemanagement.mapper.DeviceMapper;
import com.example.devicemanagement.service.DeviceService;

@RestController
@RequestMapping("api/devices")
public class DeviceController {

    @Autowired
    private DeviceService deviceService;

    @Autowired
    private DeviceMapper deviceMapper;

    @PostMapping
    public BaseResponse<DeviceRequestDTO> storeDevice(@RequestBody @Valid DeviceRequestDTO deviceRequestDTO) {
        Device rs;
        try {
            rs = deviceService.logDeviceData(deviceMapper.map(deviceRequestDTO));
        } catch (Exception ex) {
            throw new ApiException("Error when log device.", ex.getMessage());
        }
        return BaseResponse.ok(deviceMapper.map(rs));
    }

    @GetMapping("/{deviceId}")
    public BaseResponse<DeviceResponseDTO> getDetails(@PathVariable(name = "deviceId") UUID deviceId,
            @RequestParam(name = "startTime", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss") LocalDateTime startTime,
            @RequestParam(name = "endTime", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss") LocalDateTime endTime) {
        Device rs;
        try {
            rs = deviceService.getDeviceData(deviceId, startTime, endTime);
        } catch (Exception ex) {
            throw new ApiException(ex.getMessage());
        }
        return BaseResponse.ok(deviceMapper.mapDetails(rs));
    }
}
