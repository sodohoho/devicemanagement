package com.example.devicemanagement.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import com.example.devicemanagement.common.ApiException;
import com.example.devicemanagement.dto.DeviceDetailsDTO;
import com.example.devicemanagement.dto.DeviceRequestDTO;
import com.example.devicemanagement.entity.Device;
import com.example.devicemanagement.mapper.DeviceMapper;
import com.example.devicemanagement.service.DeviceService;
import com.fasterxml.jackson.databind.ObjectMapper;

@ExtendWith(SpringExtension.class)
@WebMvcTest(DeviceController.class)
class DeviceControllerTest {

    @MockBean
    private DeviceService deviceService;

    @MockBean
    private DeviceMapper deviceMapper;

    @Autowired
    MockMvc mockMvc;

    @Test
    void testStoreDevice() throws Exception {
        Device sampleRq = new Device();
        UUID randomUUID = UUID.randomUUID();
        sampleRq.setDeviceId(randomUUID);
        sampleRq.setLatitude(1d);
        sampleRq.setLongitude(1d);

        DeviceRequestDTO dto = sampleRequest(randomUUID);

        when(deviceService.logDeviceData(any())).thenReturn(sampleRq);

        mockMvc.perform(post("/api/devices").content(asJsonString(dto)).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andExpect(jsonPath("$.code", is("S01")))
                .andExpect(content().contentType("application/json")).andReturn();
    }

    @Test
    void testStoreDevice_Exception() throws Exception {
        UUID randomUUID = UUID.randomUUID();
        DeviceRequestDTO dto = sampleRequest(randomUUID);

        when(deviceService.logDeviceData(any())).thenThrow(new ApiException("Error"));

        mockMvc.perform(post("/api/devices").content(asJsonString(dto)).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest()).andExpect(jsonPath("$.code", is("E01")))
                .andExpect(jsonPath("$.error[0]", is("Error"))).andExpect(content().contentType("application/json"))
                .andReturn();
    }

    @Test
    void testGetDetails() throws Exception {
        Device sampleRq = new Device();
        UUID randomUUID = UUID.randomUUID();
        sampleRq.setDeviceId(randomUUID);
        sampleRq.setLatitude(1d);
        sampleRq.setLongitude(1d);
        DeviceRequestDTO dto = sampleRequest(randomUUID);

        when(deviceService.getDeviceData(any(), any(), any())).thenReturn(sampleRq);

        mockMvc.perform(
                get("/api/devices/" + randomUUID).content(asJsonString(dto)).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andExpect(jsonPath("$.code", is("S01")))
                .andExpect(content().contentType("application/json")).andReturn();
    }

    @Test
    void testGetDetails_Exception() throws Exception {
        UUID randomUUID = UUID.randomUUID();
        DeviceRequestDTO dto = sampleRequest(randomUUID);

        when(deviceService.getDeviceData(any(), any(), any())).thenThrow(new ApiException("Error"));

        mockMvc.perform(
                get("/api/devices/" + randomUUID).content(asJsonString(dto)).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest()).andExpect(jsonPath("$.code", is("E01")))
                .andExpect(jsonPath("$.error[0]", is("Error"))).andExpect(content().contentType("application/json"))
                .andReturn();
    }

    private DeviceRequestDTO sampleRequest(UUID randomUUID) {
        DeviceRequestDTO dto = new DeviceRequestDTO();
        dto.setDeviceId(randomUUID);
        dto.setLatitude(1d);
        dto.setLongitude(1d);
        dto.setData(new DeviceDetailsDTO());
        return dto;
    }

    private String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
