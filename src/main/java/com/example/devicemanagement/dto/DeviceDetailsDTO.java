package com.example.devicemanagement.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeviceDetailsDTO {

    private Integer humidity;

    private TemparatureDTO temparature;

    private LocalDateTime timestamp;

}
