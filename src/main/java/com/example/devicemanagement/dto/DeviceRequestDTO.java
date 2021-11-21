package com.example.devicemanagement.dto;

import java.util.UUID;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class DeviceRequestDTO {

    @NotNull(message = "Device id can not be null")
    private UUID deviceId;

    @NotNull(message = "latitude can not be null")
    private Double latitude;

    @NotNull(message = "longitude can not be null")
    private Double longitude;

    @NotNull(message = "details can not be null")
    private DeviceDetailsDTO data;

}
