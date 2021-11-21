package com.example.devicemanagement.dto;

import java.util.List;
import java.util.UUID;

import lombok.Data;

@Data
public class DeviceResponseDTO {

    private UUID deviceId;

    private Double latitude;

    private Double longitude;

    private List<DeviceDetailsDTO> data;

}
