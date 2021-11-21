package com.example.devicemanagement.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

import com.example.devicemanagement.entity.DeviceDetails;

@Repository
public interface DeviceDetailsRepository extends CassandraRepository<DeviceDetails, UUID> {

    List<DeviceDetails> findByDeviceIdAndTimestampBetween(UUID id, LocalDateTime startTime, LocalDateTime endTime);
}
