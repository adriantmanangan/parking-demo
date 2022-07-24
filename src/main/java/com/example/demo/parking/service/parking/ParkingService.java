package com.example.demo.parking.service.parking;

import com.example.demo.parking.dto.ParkingDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ParkingService {

    ResponseEntity<ParkingDto> createParking(ParkingDto parkingDto);

    ResponseEntity<List<ParkingDto>> createParkingList(List <ParkingDto> parkingDto);

    ResponseEntity<ParkingDto> getAvailableParkingSlot(String reference);
}
