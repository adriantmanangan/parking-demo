package com.example.demo.parkingslot.controller;

import javax.validation.Valid;

import com.example.demo.base.service.response.ResponseService;
import com.example.demo.parking.dto.ParkingDto;
import com.example.demo.parkingslot.dto.ParkingSlotDto;
import com.example.demo.parkingslot.service.ParkingSlotService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class ParkingSlotController {

    private final ParkingSlotService parkingSlotService;

    @PostMapping("/api/parkingSlot/{parkingNumber}")
    public ResponseEntity<ParkingDto> addParkingSlot(@RequestBody ParkingSlotDto parkingSlotDto, @PathVariable String parkingNumber){
       return parkingSlotService.addParkingSlot(parkingSlotDto, parkingNumber);
    }

}
