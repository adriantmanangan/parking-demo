package com.example.demo.parkingslot.service;

import java.util.Optional;

import com.example.demo.parkingslot.dto.ParkingSlotDto;
import com.example.demo.parkingslot.mapper.ParkingSlotContext;
import com.example.demo.parkingslot.mapper.ParkingSlotMapper;
import com.example.demo.parkingslot.repository.ParkingSlotRepository;
import com.example.demo.base.service.response.ResponseService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class ParkingSlotServiceImpl implements ParkingSlotService{

    private final ParkingSlotRepository parkingSlotRepository;
    private final ParkingSlotMapper parkingSlotMapper;
    private final ResponseService responseService;
    private final ParkingSlotContext parkingSlotContext;

    @Override
    public ResponseEntity<ParkingSlotDto> createParkingSlot(ParkingSlotDto parkingSlotDto) {
     return Optional.of(parkingSlotMapper.mapToParkingSlot(parkingSlotDto,parkingSlotContext))
         .map(parkingSlotRepository::save)
         .map(parkingSlotMapper::mapToParkingSlotDto)
         .map(responseService::ok)
         .orElseGet(responseService::badRequest);
    }

}
