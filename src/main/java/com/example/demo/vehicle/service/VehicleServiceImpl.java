package com.example.demo.vehicle.service;

import static com.example.demo.base.converter.SizeEnumConverter.sizeToInteger;

import java.time.LocalDateTime;
import java.util.Optional;

import com.example.demo.base.constants.ResponseMessageCode;
import com.example.demo.base.constants.SuccessMessageResponse;
import com.example.demo.parkingslot.dto.ParkingSlotDto;
import com.example.demo.vehicle.dto.VehicleDto;
import com.example.demo.parkingslot.mapper.ParkingSlotContext;
import com.example.demo.parkingslot.mapper.ParkingSlotMapper;
import com.example.demo.vehicle.mapper.VehicleMapper;
import com.example.demo.parkingslot.model.ParkingSlot;
import com.example.demo.vehicle.model.Vehicle;
import com.example.demo.parkingslot.repository.ParkingSlotRepository;
import com.example.demo.vehicle.repository.VehicleRepository;
import com.example.demo.rate.service.FeeService;
import com.example.demo.base.service.response.ResponseService;
import com.example.demo.parking.utils.DateUtils;
import com.example.demo.parking.utils.ParkingSlotUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class VehicleServiceImpl implements VehicleService {

    private final ParkingSlotRepository parkingSlotRepository;
    private final VehicleRepository vehicleRepository;
    private final ResponseService responseService;
    private final VehicleMapper vehicleMapper;
    private final ParkingSlotContext parkingSlotContext;
    private final FeeService feeService;
    private final ParkingSlotMapper parkingSlotMapper;

    /**
     * @param vehicleDto vehicleDto
     * @return SuccessMessageResponse for vehicle parking
     */
    @Override
    public SuccessMessageResponse addToParkingSlot(VehicleDto vehicleDto) {
        Vehicle existingVehicle = vehicleRepository.findByPlateNumberAndIsPark(vehicleDto.getPlateNumber(), false);
        ParkingSlot parkingSlot = availableParkingSlot(vehicleDto).get();
        parkingSlotContext.setParkingSlot(parkingSlot);
        if(existingVehicle != null){
            updateExistingVehicle(vehicleDto,existingVehicle);
        }else{
            vehicleMapper.mapToVehicle(vehicleDto, parkingSlotContext);
        }
        parkingSlotRepository.save(parkingSlot);
        return responseService.createSuccessfulMessageResponse(ResponseMessageCode.SUCCESS_PARK, vehicleDto.getPlateNumber());

    }


    @Override
    public Optional<ParkingSlot> availableParkingSlot(VehicleDto vehicleDto) {
        return parkingSlotRepository.findAllByIsAvailableAndSize(true, sizeToInteger(vehicleDto.getSize()))
            .map(ParkingSlotUtils::getNearestParkingSlot);
    }

    /**
     * Remove vehicle from parking slot, but
     * @param vehicleDto vehicleDto
     * @return Message response sucess
     */
    @Override
    public SuccessMessageResponse unparkVehicle(VehicleDto vehicleDto) {
        Vehicle parkedVehicle = vehicleRepository.findByPlateNumberAndIsPark(vehicleDto.getPlateNumber(), true);
        vehicleMapper.updateVehicleDto(parkedVehicle,vehicleDto,parkingSlotContext);
        ParkingSlot parkingSlot = parkingSlotRepository.findByVehicle(parkedVehicle).orElseGet(null);
        ParkingSlotDto parkingSlotDto = parkingSlotMapper.mapToParkingSlotDto(parkingSlot);
        applyFees(vehicleDto,parkingSlotDto);
        updateParkingAndVehicle(vehicleDto,parkedVehicle,parkingSlot);
        return responseService.createSuccessfulMessageResponse(ResponseMessageCode.SUCCESS_UNPARK, vehicleDto.getFee());
    }

    /**
     * Checks if applicable for fees by checking the vehicleEntryTime
     * @param vehicleDto vehicleDto
     * @param parkingSlotDto parkingSlotDto
     */
    @Override
    public void applyFees(VehicleDto vehicleDto, ParkingSlotDto parkingSlotDto) {
        if(DateUtils.getDifferenceInMinutes(vehicleDto.getEntryTime(),vehicleDto.getExitTime()) > 60){
            feeService.compute(vehicleDto, parkingSlotDto);
        }
    }

    @Override
    public void updateExistingVehicle(VehicleDto vehicleDto, Vehicle existingVehicle){
        vehicleDto.setReturnTime(LocalDateTime.now());
        vehicleMapper.updateVehicle(vehicleDto,existingVehicle,parkingSlotContext);
    }

    @Override
    public void updateParkingAndVehicle(VehicleDto vehicleDto, Vehicle parkedVehicle, ParkingSlot parkingSlot ){
        vehicleMapper.updateVehicle(vehicleDto,parkedVehicle,parkingSlotContext);
        parkingSlot.removeVehicle();
        parkingSlotRepository.save(parkingSlot);
    }


}