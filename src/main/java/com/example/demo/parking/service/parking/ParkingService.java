package com.example.demo.parking.service.parking;

import java.util.List;

import com.example.demo.parking.dto.ParkingDto;
import org.springframework.http.ResponseEntity;

public interface ParkingService {

    ResponseEntity<ParkingDto> createParking(ParkingDto parkingDto);

    ResponseEntity<List<ParkingDto>> createParkingList(List <ParkingDto> parkingDto);

    ResponseEntity<ParkingDto> getAvailableParkingSlot(String reference);
}
