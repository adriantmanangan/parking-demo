package com.example.demo.parking.service.parking;

import com.example.demo.base.ResponseErrorCode;
import com.example.demo.base.service.response.ResponseService;
import com.example.demo.parking.dto.ParkingDto;
import com.example.demo.parking.exception.ParkingException;
import com.example.demo.parking.mapper.ParkingMapper;
import com.example.demo.parking.repository.ParkingRepository;
import com.example.demo.parkingslot.mapper.ParkingSlotContext;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Component
public class ParkingServiceImpl implements ParkingService{

    private final ParkingRepository parkingRepository;
    private final ParkingMapper parkingMapper;
    private final ResponseService responseService;
    private final ParkingSlotContext parkingSlotContext;

    @Override
    public ResponseEntity<ParkingDto> createParking(ParkingDto parkingDto) {
        return Optional.of(parkingMapper.mapToParking(parkingDto,parkingSlotContext))
            .map(parkingRepository::save)
            .map(parkingMapper::mapToParkingDto)
            .map(responseService::ok)
            .orElseGet(responseService::badRequest);
    }

    @Override
    public ResponseEntity<List<ParkingDto>> createParkingList(List<ParkingDto> parkingDto) {
        return Optional.of(parkingMapper.mapToParkingList(parkingDto,parkingSlotContext))
            .map(parkingRepository::saveAll)
            .map(parkingMapper::mapToParkingDtoList)
            .map(responseService::ok)
            .orElseGet(responseService::badRequest);
    }

    @Override
    public ResponseEntity<ParkingDto> getAvailableParkingSlot(String reference) {
        return parkingRepository.findByParkingNumber(reference)
            .map(parkingMapper::mapToParkingDto)
            .map(responseService::ok)
            .orElseThrow(() -> new ParkingException(ResponseErrorCode.PARKING_NOT_FOUND, reference));
    }

}
