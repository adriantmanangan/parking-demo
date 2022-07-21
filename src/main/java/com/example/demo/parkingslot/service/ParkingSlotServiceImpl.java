package com.example.demo.parkingslot.service;

import java.util.Optional;
import javax.validation.Valid;

import com.example.demo.base.ResponseErrorCode;
import com.example.demo.base.service.response.ResponseService;
import com.example.demo.parking.repository.ParkingRepository;
import com.example.demo.parkingslot.dto.ParkingSlotDto;
import com.example.demo.parkingslot.exception.ParkingSlotException;
import com.example.demo.parkingslot.mapper.ParkingSlotContext;
import com.example.demo.parkingslot.mapper.ParkingSlotMapper;
import com.example.demo.parkingslot.repository.ParkingSlotRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

@AllArgsConstructor
@Component
public class ParkingSlotServiceImpl implements ParkingSlotService {

    private final ParkingSlotRepository parkingSlotRepository;
    private final ParkingSlotMapper parkingSlotMapper;
    private final ResponseService responseService;
    private final ParkingSlotContext parkingSlotContext;
    private final ParkingRepository parkingRepository;

    @Override
    public ResponseEntity<ParkingSlotDto> createParkingSlot(@RequestBody @Valid ParkingSlotDto parkingSlotDto) {
        Optional.of(parkingRepository.findByParkingNumber(parkingSlotDto.getParkingDto().getParkingNumber()))
            .orElseThrow(() -> new ParkingSlotException(ResponseErrorCode.PARKING_NOT_FOUND, parkingSlotDto.getParkingDto().getParkingNumber()));

        return Optional.of(parkingSlotMapper.mapToParkingSlot(parkingSlotDto, parkingSlotContext))
            .map(parkingSlotRepository::save)
            .map(parkingSlotMapper::mapToParkingSlotDto)
            .map(responseService::ok)
            .orElseGet(responseService::badRequest);
    }
}
