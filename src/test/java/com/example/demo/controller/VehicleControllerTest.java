package com.example.demo.controller;

import com.example.demo.base.constants.SuccessMessageResponse;
import com.example.demo.base.service.response.ResponseService;
import com.example.demo.builder.VehicleTest;
import com.example.demo.vehicle.controller.VehicleController;
import com.example.demo.vehicle.dto.VehicleDto;
import com.example.demo.vehicle.service.VehicleService;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
        Mockito.when(vehicleService.addToParkingSlot(Mockito.any(VehicleDto.class), Mockito.any(String.class))).thenReturn(successMessageResponse);
        Mockito.when(responseService.ok(Mockito.any(VehicleDto.class))).thenReturn(ResponseEntity.ok(vehicleDto));

        mockMvc.perform(post("/api/vehicle/park")
            .content(objectMapper.writeValueAsString(newVehicleDto()))
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().is2xxSuccessful());

    }
}
