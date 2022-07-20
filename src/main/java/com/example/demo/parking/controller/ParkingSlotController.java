package com.example.demo.parking.controller;

import com.example.demo.parking.dto.ParkingSlotDto;
import com.example.demo.parking.dto.VehicleDto;
import com.example.demo.parking.model.ParkingSlot;
import com.example.demo.parking.service.parkingslot.ParkingSlotService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class ParkingSlotController {
    
    private final ParkingSlotService parkingSlotService;

    @PostMapping("/api/createParkingSlot")
    public ResponseEntity<ParkingSlotDto> createParkingSlot(@RequestBody ParkingSlotDto parkingSlotDto){
        return parkingSlotService.createParkingSlot(parkingSlotDto);
    }



}
