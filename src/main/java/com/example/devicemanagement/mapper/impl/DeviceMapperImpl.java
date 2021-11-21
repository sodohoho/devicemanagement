package com.example.devicemanagement.mapper.impl;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.example.devicemanagement.dto.DeviceDetailsDTO;
import com.example.devicemanagement.dto.DeviceRequestDTO;
import com.example.devicemanagement.dto.DeviceResponseDTO;
import com.example.devicemanagement.dto.TemparatureDTO;
import com.example.devicemanagement.entity.Device;
import com.example.devicemanagement.entity.DeviceDetails;
import com.example.devicemanagement.mapper.DeviceMapper;

@Component
public class DeviceMapperImpl implements DeviceMapper {

    @Override
    public DeviceRequestDTO map(Device src) {
        // map device
        DeviceRequestDTO dst = new DeviceRequestDTO();
        dst.setDeviceId(src.getDeviceId());
        dst.setLatitude(src.getLatitude());
        dst.setLongitude(src.getLongitude());

        // map device detail
        DeviceDetailsDTO detailsDto = new DeviceDetailsDTO();
        if (!CollectionUtils.isEmpty(src.getData())) {
            DeviceDetails deviceDetails = src.getData().get(0);
            detailsDto.setHumidity(deviceDetails.getHumidity());
            detailsDto.setTemparature(new TemparatureDTO(deviceDetails.getUnit(), deviceDetails.getValue()));
            detailsDto.setTimestamp(deviceDetails.getTimestamp());
        }

        // map
        dst.setData(detailsDto);
        return dst;
    }

    @Override
    public Device map(DeviceRequestDTO src) {
        // map device
        Device dst = new Device();
        dst.setDeviceId(src.getDeviceId());
        dst.setLatitude(src.getLatitude());
        dst.setLongitude(src.getLongitude());

        // map device detail
        DeviceDetails details = new DeviceDetails();
        details.setDeviceId(src.getDeviceId());
        details.setHumidity(src.getData().getHumidity());
        TemparatureDTO temparature = src.getData().getTemparature();
        if (temparature != null) {
            details.setUnit(temparature.getUnit());
            details.setValue(temparature.getValue());
        }
        // map
        dst.setData(Arrays.asList(details));
        return dst;
    }

    @Override
    public DeviceResponseDTO mapDetails(Device src) {
        // map device
        DeviceResponseDTO dst = new DeviceResponseDTO();
        dst.setDeviceId(src.getDeviceId());
        dst.setLatitude(src.getLatitude());
        dst.setLongitude(src.getLongitude());

        // map device detail
        List<DeviceDetailsDTO> detailsDto = src.getData().stream()
                .map(data -> new DeviceDetailsDTO(data.getHumidity(),
                        new TemparatureDTO(data.getUnit(), data.getValue()), data.getTimestamp()))
                .collect(Collectors.toList());

        // map
        dst.setData(detailsDto);
        return dst;
    }

}
