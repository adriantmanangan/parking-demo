package com.example.demo.parking.service.parkingslot;

import com.example.demo.parking.dto.ParkingSlotDto;
import org.springframework.http.ResponseEntity;

public interface ParkingSlotService {

    ResponseEntity<ParkingSlotDto> createParkingSlot(ParkingSlotDto parkingSlotDto);

}
