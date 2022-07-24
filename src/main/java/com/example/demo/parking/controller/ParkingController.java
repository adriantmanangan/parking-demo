package com.example.demo.parking.controller;

import java.util.List;
import javax.validation.Valid;

import com.example.demo.parking.dto.ParkingDto;
import com.example.demo.parking.service.parking.ParkingService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
