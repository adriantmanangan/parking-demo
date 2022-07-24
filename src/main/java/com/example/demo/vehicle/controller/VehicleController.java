package com.example.demo.vehicle.controller;

import com.example.demo.base.constants.SuccessMessageResponse;
import com.example.demo.base.service.response.ResponseService;
import com.example.demo.vehicle.dto.VehicleDto;
import com.example.demo.vehicle.dto.VehicleUnparkDto;
import com.example.demo.vehicle.service.VehicleService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@AllArgsConstructor
public class VehicleController {
    private final VehicleService vehicleService;
    private final ResponseService responseService;

    @PostMapping(value = "/api/vehicle/park/{parkingNumber}",consumes = APPLICATION_JSON_VALUE )
    public ResponseEntity<SuccessMessageResponse> parkVehicle(@RequestBody VehicleDto vehicleDto, @PathVariable String parkingNumber) {
        return responseService.ok(vehicleService.parkVehicle(vehicleDto,parkingNumber));
    }

    @PutMapping(value = "/api/vehicle/unpark/{parkingNumber}",consumes = APPLICATION_JSON_VALUE )
    public ResponseEntity <SuccessMessageResponse> unparkVehicle(@RequestBody VehicleUnparkDto vehicleDto, @PathVariable String parkingNumber){
        return responseService.ok(vehicleService.unparkVehicle(vehicleDto,parkingNumber));
    }
}
