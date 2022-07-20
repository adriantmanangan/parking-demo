package com.example.demo.parking.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import com.example.demo.parking.dto.ParkingDto;
import com.example.demo.parking.service.parking.ParkingService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class ParkingController {

    private final ParkingService parkingService;

    @PostMapping("/api/createParking")
    public ResponseEntity<ParkingDto> createParkingSlot(@RequestBody ParkingDto parkingDto){
        return parkingService.createParking(parkingDto);
    }

    @PostMapping("/api/createParkingList")
    public ResponseEntity<List<ParkingDto>> createParkingSlotList(@RequestBody List<ParkingDto> parkingDtoList){
        return parkingService.createParkingList(parkingDtoList);
    }

    @GetMapping("/api/getParking/{reference}")
    public ResponseEntity<ParkingDto> getParkingSlots(@PathVariable("reference") String reference){
        return parkingService.getAvailableParkingSlot(reference);
    }
}
