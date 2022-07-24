package com.example.demo.parkingslot.service;

import com.example.demo.base.ResponseErrorCode;
import com.example.demo.base.service.response.ResponseService;
import com.example.demo.parking.dto.ParkingDto;
import com.example.demo.parking.mapper.ParkingMapper;
import com.example.demo.parking.model.Parking;
import com.example.demo.parking.repository.ParkingRepository;
import com.example.demo.parkingslot.dto.ParkingSlotDto;
import com.example.demo.parkingslot.exception.ParkingSlotException;
import com.example.demo.parkingslot.mapper.ParkingSlotContext;
import com.example.demo.parkingslot.mapper.ParkingSlotMapper;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.Optional;

@AllArgsConstructor
@Component
public class ParkingSlotServiceImpl implements ParkingSlotService {
    private final ParkingSlotMapper parkingSlotMapper;
    private final ResponseService responseService;
    private final ParkingSlotContext parkingSlotContext;
    private final ParkingRepository parkingRepository;

    private final ParkingMapper parkingMapper;

    @Override
    public ResponseEntity<ParkingDto> addParkingSlot(ParkingSlotDto parkingSlotDto, String parkingNumber) {
        Parking parking = parkingRepository.findByParkingNumber(parkingSlotDto.getParkingDto().getParkingNumber())
                .orElseThrow(() -> new ParkingSlotException(ResponseErrorCode.PARKING_NOT_FOUND, parkingSlotDto.getParkingDto().getParkingNumber()));

        return Optional.of(parkingSlotMapper.mapToParkingSlot(parkingSlotDto, parkingSlotContext))
                .map(parkingSlot -> parkingSlotMapper.mapParkingToParkingSlot(parking, parkingSlot, parkingSlotContext))
                .map(parkingRepository::save)
                .map(parkingMapper::mapToParkingDto)
                .map(responseService::ok)
                .orElse(responseService.badRequest());
    }
}


