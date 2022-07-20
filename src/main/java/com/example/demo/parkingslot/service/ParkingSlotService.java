package com.example.demo.parkingslot.service;

import com.example.demo.parkingslot.dto.ParkingSlotDto;
import org.springframework.http.ResponseEntity;

public interface ParkingSlotService {

    ResponseEntity<ParkingSlotDto> createParkingSlot(ParkingSlotDto parkingSlotDto);

}
