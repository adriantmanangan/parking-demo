package com.example.demo.parkingslot.service;

import com.example.demo.parking.dto.ParkingDto;
import com.example.demo.parkingslot.dto.ParkingSlotDto;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;

public interface ParkingSlotService {
    ResponseEntity <ParkingDto> addParkingSlot(ParkingSlotDto parkingSlotDto, String parkingNumber);

}
