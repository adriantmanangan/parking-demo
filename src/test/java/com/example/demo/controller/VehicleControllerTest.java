package com.example.demo.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.demo.builder.VehicleTest;
import com.example.demo.parking.constants.SuccessMessageResponse;
import com.example.demo.parking.controller.VehicleController;
import com.example.demo.parking.dto.VehicleDto;
import com.example.demo.parking.service.response.ResponseService;
import com.example.demo.parking.service.vehicle.VehicleService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@WebMvcTest(controllers = VehicleController.class)
public class VehicleControllerTest implements VehicleTest{


    @MockBean
    private VehicleService vehicleService;

    @MockBean
    private ResponseService responseService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @BeforeEach
    void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void parkVehicle() throws Exception {
        VehicleDto vehicleDto = newVehicleDto();
        SuccessMessageResponse successMessageResponse = new SuccessMessageResponse();
        Mockito.when(vehicleService.addToParkingSlot(Mockito.any(VehicleDto.class))).thenReturn(successMessageResponse);
        Mockito.when(responseService.ok(Mockito.any(VehicleDto.class))).thenReturn(ResponseEntity.ok(vehicleDto));

        mockMvc.perform(post("/api/vehicle/park")
            .content(objectMapper.writeValueAsString(newVehicleDto()))
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().is2xxSuccessful());

    }
}
