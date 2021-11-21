package com.example.devicemanagement.mapper;

import com.example.devicemanagement.dto.DeviceRequestDTO;
import com.example.devicemanagement.dto.DeviceResponseDTO;
import com.example.devicemanagement.entity.Device;

public interface DeviceMapper {

    DeviceRequestDTO map(Device device);

    Device map(DeviceRequestDTO deviceRequestDTO);

    DeviceResponseDTO mapDetails(Device rs);
}
