package com.example.demo.parking.controller;

import com.example.demo.parking.constants.SuccessMessageResponse;
import com.example.demo.parking.dto.VehicleDto;
import com.example.demo.parking.service.response.ResponseService;
import com.example.demo.parking.service.vehicle.VehicleService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class VehicleController {

    private final VehicleService vehicleService;
    private final ResponseService responseService;

    @PostMapping("/api/vehicle/park")
    public ResponseEntity<SuccessMessageResponse> parkVehicle(@RequestBody VehicleDto vehicleDto) {
        return responseService.ok(vehicleService.addToParkingSlot(vehicleDto));
    }

    @PutMapping("/api/vehicle/unpark")
    public ResponseEntity <SuccessMessageResponse> unparkVehicle(@RequestBody VehicleDto vehicleDto){
        return responseService.ok(vehicleService.unparkVehicle(vehicleDto));
    }
}
