package com.example.demo.parking.controller;

import com.example.demo.parking.dto.ParkingDto;
import com.example.demo.parking.service.parking.ParkingService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@AllArgsConstructor
@Validated
public class ParkingController {

    private final ParkingService parkingService;

    @PostMapping("/api/parking")
    public ResponseEntity<ParkingDto> createParkingSlot(@RequestBody @Valid ParkingDto parkingDto){
        return parkingService.createParking(parkingDto);
    }

    @PostMapping("/api/parkings")
    public ResponseEntity<List<ParkingDto>> createParkingSlotList(@RequestBody @Valid List<ParkingDto> parkingDtoList){
        return parkingService.createParkingList(parkingDtoList);
    }

    @GetMapping("/api/parking/{reference}")
    public ResponseEntity<ParkingDto> getParkingSlots(@PathVariable("reference") String reference){
        return parkingService.getAvailableParkingSlot(reference);
    }
}
