package com.example.demo.vehicle.service;

import static com.example.demo.base.ResponseErrorCode.VEHICLE_NOT_FOUND;
import static com.example.demo.base.ResponseErrorCode.VEHICLE_PARKING_LOT_NOT_FOUND;
import static com.example.demo.base.converter.SizeEnumConverter.sizeToInteger;

import java.time.LocalDateTime;
import java.util.Optional;

import com.example.demo.base.ResponseErrorCode;
import com.example.demo.base.constants.ResponseMessageCode;
import com.example.demo.base.constants.SuccessMessageResponse;
import com.example.demo.base.service.response.ResponseService;
import com.example.demo.parking.exception.ParkingException;
import com.example.demo.parkingslot.utils.ParkingSlotUtils;
import com.example.demo.base.utils.TimeUtils;
import com.example.demo.parkingslot.dto.ParkingSlotDto;
import com.example.demo.parkingslot.mapper.ParkingSlotContext;
import com.example.demo.parkingslot.mapper.ParkingSlotMapper;
import com.example.demo.parkingslot.model.ParkingSlot;
import com.example.demo.parkingslot.repository.ParkingSlotRepository;
import com.example.demo.fee.service.FeeService;
import com.example.demo.vehicle.dto.VehicleDto;
import com.example.demo.vehicle.mapper.VehicleMapper;
import com.example.demo.vehicle.model.Vehicle;
import com.example.demo.vehicle.repository.VehicleRepository;
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

    @Override
    public SuccessMessageResponse parkVehicle(VehicleDto vehicleDto) {
        Optional <Vehicle> existingVehicle = vehicleRepository.findByPlateNumberAndIsPark(vehicleDto.getPlateNumber(), false);
        existingVehicle.ifPresentOrElse(vehicle -> updateToParkingSlot(vehicleDto, vehicle), () -> addToParkingSlot(vehicleDto));;
        return responseService.createSuccessfulMessageResponse(ResponseMessageCode.SUCCESS_PARK, vehicleDto.getPlateNumber());
    }


    /**
     * @param vehicleDto vehicleDto
     * @return SuccessMessageResponse for vehicle parking
     */
    @Override
    public SuccessMessageResponse addToParkingSlot(VehicleDto vehicleDto) {
        ParkingSlot parkingSlot = availableParkingSlot(vehicleDto).get();
        parkingSlotContext.setParkingSlot(parkingSlot);
        vehicleMapper.mapToVehicle(vehicleDto, parkingSlotContext);
        parkingSlotRepository.save(parkingSlot);
        return responseService.createSuccessfulMessageResponse(ResponseMessageCode.SUCCESS_PARK, vehicleDto.getPlateNumber());

    }

    @Override
    public SuccessMessageResponse updateToParkingSlot(VehicleDto vehicleDto, Vehicle existingVehicle) {
        ParkingSlot parkingSlot = availableParkingSlot(vehicleDto).get();
        parkingSlotContext.setParkingSlot(parkingSlot);
        updateExistingVehicle(vehicleDto,existingVehicle);
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
        Vehicle parkedVehicle = vehicleRepository.findByPlateNumberAndIsPark(vehicleDto.getPlateNumber(), true)
            .orElseThrow(() ->new ParkingException(VEHICLE_NOT_FOUND, vehicleDto.getPlateNumber()));

        ParkingSlot vehicleParkingSlot = parkingSlotRepository.findByVehicle(parkedVehicle)
            .orElseThrow(() ->new ParkingException(VEHICLE_PARKING_LOT_NOT_FOUND, vehicleDto.getPlateNumber()));

        applyFees(vehicleDto, vehicleParkingSlot);
        updateParkingAndVehicle(vehicleDto,parkedVehicle,vehicleParkingSlot);
        return responseService.createSuccessfulMessageResponse(ResponseMessageCode.SUCCESS_UNPARK, vehicleDto.getFee());
    }

    /**
     * Checks if applicable for fees by checking the vehicleEntryTime
     * @param vehicleDto vehicleDto
     * @param parkingSlot parkingSlotDto
     */
    @Override
    public void applyFees(VehicleDto vehicleDto, ParkingSlot parkingSlot) {
         feeService.compute(vehicleDto, parkingSlot);
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
