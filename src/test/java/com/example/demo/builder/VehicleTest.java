package com.example.demo.builder;

import com.example.demo.vehicle.dto.VehicleDto;

public interface VehicleTest{

    default VehicleDto newVehicleDto(){
        return VehicleDto.builder()
            .plateNumber("VEHICLE_DTO").build();
    }
}
