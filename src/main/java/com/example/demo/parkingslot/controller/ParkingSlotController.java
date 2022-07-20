package com.example.demo.parkingslot.controller;

import com.example.demo.parkingslot.dto.ParkingSlotDto;
import com.example.demo.parkingslot.service.ParkingSlotService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class ParkingSlotController {
    
    private final ParkingSlotService parkingSlotService;

    @PostMapping("/api/createParkingSlot")
    public ResponseEntity<ParkingSlotDto> createParkingSlot(@RequestBody ParkingSlotDto parkingSlotDto, Errors errors){
        return parkingSlotService.createParkingSlot(parkingSlotDto, errors);
    }

}
