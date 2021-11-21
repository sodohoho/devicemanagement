package com.example.devicemanagement.entity;

import java.util.List;
import java.util.UUID;

import org.springframework.data.annotation.Transient;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import lombok.Data;

@Data
@Table("device")
public class Device {

    @PrimaryKey
    @Column(value = "device_id")
    private UUID deviceId;

    private Double latitude;

    private Double longitude;

    @Transient
    private List<DeviceDetails> data;
}
