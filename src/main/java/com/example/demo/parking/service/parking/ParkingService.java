package com.example.demo.parking.service.parking;

import java.util.List;

import com.example.demo.base.constants.SuccessMessageResponse;
import com.example.demo.parking.dto.ParkingDto;
import org.springframework.http.ResponseEntity;

public interface ParkingService {

    ResponseEntity<ParkingDto> createParking(ParkingDto parkingDto);

    ResponseEntity<List<ParkingDto>> createParkingList(List <ParkingDto> parkingDto);

    SuccessMessageResponse getAvailableParkingSlot(String reference);
}
