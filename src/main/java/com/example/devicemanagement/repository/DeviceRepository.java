package com.example.devicemanagement.repository;

import java.util.UUID;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

import com.example.devicemanagement.entity.Device;

@Repository
public interface DeviceRepository extends CassandraRepository<Device, UUID> {

}
