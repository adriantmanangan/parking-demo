package com.example.demo.parking.controller;

import java.util.List;
import javax.validation.Valid;

import com.example.demo.base.constants.SuccessMessageResponse;
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
    public ResponseEntity<ParkingDto> createParkingSlot(@RequestBody @Valid ParkingDto parkingDto){
        return parkingService.createParking(parkingDto);
    }

    @PostMapping("/api/createParkingList")
    public ResponseEntity<List<ParkingDto>> createParkingSlotList(@RequestBody List<ParkingDto> parkingDtoList){
        return parkingService.createParkingList(parkingDtoList);
    }

    @GetMapping("/api/getParking/{reference}")
    public SuccessMessageResponse getParkingSlots(@PathVariable("reference") String reference){
        return parkingService.getAvailableParkingSlot(reference);
    }
}
