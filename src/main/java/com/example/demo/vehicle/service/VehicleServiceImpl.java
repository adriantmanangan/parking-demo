package com.example.demo.vehicle.service;

import com.example.demo.base.constants.ResponseMessageCode;
import com.example.demo.base.constants.Size;
import com.example.demo.base.constants.SuccessMessageResponse;
import com.example.demo.base.service.response.ResponseService;
import com.example.demo.base.utils.TimeUtils;
import com.example.demo.fee.constants.TimeConstants;
import com.example.demo.fee.service.FeeService;
import com.example.demo.parking.exception.ParkingException;
import com.example.demo.parking.model.Parking;
import com.example.demo.parking.repository.ParkingRepository;
import com.example.demo.parkingslot.mapper.ParkingSlotContext;
import com.example.demo.parkingslot.model.ParkingSlot;
import com.example.demo.parkingslot.utils.ParkingSlotUtils;
import com.example.demo.vehicle.dto.VehicleDto;
import com.example.demo.vehicle.dto.VehicleUnparkDto;
import com.example.demo.vehicle.mapper.VehicleMapper;
import com.example.demo.vehicle.model.Vehicle;
import com.example.demo.vehicle.repository.VehicleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static com.example.demo.base.ResponseErrorCode.VEHICLE_NOT_FOUND;

@AllArgsConstructor
@Component
public class VehicleServiceImpl implements VehicleService {

    private final VehicleRepository vehicleRepository;
    private final ResponseService responseService;
    private final VehicleMapper vehicleMapper;
    private final ParkingSlotContext parkingSlotContext;
    private final FeeService feeService;
    private final ParkingRepository parkingRepository;

    @Override
    public SuccessMessageResponse parkVehicle(VehicleDto vehicleDto, String parkingNumber) {
        Optional<Vehicle> existingVehicle = vehicleRepository.findByPlateNumberAndIsPark(vehicleDto.getPlateNumber(), false);
        existingVehicle.ifPresentOrElse(vehicle -> updateToParkingSlot(vehicleDto, vehicle, parkingNumber),
                () -> addToParkingSlot(vehicleDto, parkingNumber));
        return responseService.createSuccessfulMessageResponse(ResponseMessageCode.SUCCESS_PARK, vehicleDto.getPlateNumber());
    }

    /**
     * @param vehicleDto vehicleDto
     * @return SuccessMessageResponse for vehicle parking
     */
    @Override
    public SuccessMessageResponse addToParkingSlot(VehicleDto vehicleDto, String reference) {
        Parking parking = parkingRepository.findByParkingNumber(reference).get();
        ParkingSlot parkingSlot = availableParkingSlot(parking.getParkingSlotList(), vehicleDto.getSize());
        parkingSlotContext.setParkingAndParkingSlot(parking,parkingSlot);
        parkingSlot.setIsAvailable(false);
        vehicleMapper.parkVehicle(vehicleDto, parkingSlotContext);
        parkingRepository.save(parking);
        return responseService.createSuccessfulMessageResponse(ResponseMessageCode.SUCCESS_PARK, vehicleDto.getPlateNumber());

    }

    @Override
    public SuccessMessageResponse updateToParkingSlot(VehicleDto vehicleDto, Vehicle existingVehicle,
                                                      String parkingNumber) {
        Parking parking = parkingRepository.findByParkingNumber(parkingNumber).get();
        ParkingSlot parkingSlot = availableParkingSlot(parking.getParkingSlotList(), vehicleDto.getSize());
        parkingSlotContext.setParkingAndParkingSlot(parking,parkingSlot);
        parkingSlot.setIsAvailable(false);
        updateExistingVehicle(vehicleDto, existingVehicle);
        parkingRepository.save(parking);
        return responseService.createSuccessfulMessageResponse(ResponseMessageCode.SUCCESS_PARK, vehicleDto.getPlateNumber());
    }


    public ParkingSlot availableParkingSlot(List<ParkingSlot> parkingSlotList, Size size) {
        return ParkingSlotUtils.getNearestParkingSlot(parkingSlotList,size);
    }

    /**
     * Remove vehicle from parking slot, but
     *
     * @param vehicleUnparkDto vehicleDto
     * @return Message response sucess
     */
    @Override
    public SuccessMessageResponse unparkVehicle(VehicleUnparkDto vehicleUnparkDto, String parkingNumber) {
        Parking parking = parkingRepository.findByParkingNumber(parkingNumber).get();
        ParkingSlot parkingSlot = availableParkingSlot(parking.getParkingSlotList(), vehicleUnparkDto.getSize());
        parkingSlotContext.setParkingAndParkingSlot(parking,parkingSlot);
        parkingSlot.setIsAvailable(true);
        VehicleDto vehicleDto = vehicleMapper.mapToVehicleDto(vehicleUnparkDto);
        Vehicle parkedVehicle = vehicleRepository.findByPlateNumberAndIsPark(vehicleDto.getPlateNumber(), true).
                orElseThrow(() -> new ParkingException(VEHICLE_NOT_FOUND, vehicleDto.getPlateNumber()));
        updateExistingVehicle(vehicleDto, parkedVehicle);
        applyFees(vehicleDto, parkingSlot);
        updateParkingAndVehicle(vehicleDto, parkedVehicle, parkingSlot);
        parkingRepository.save(parking);
        return responseService.createSuccessfulMessageResponse(ResponseMessageCode.SUCCESS_UNPARK, vehicleDto.getFee());
    }

    /**
     * Checks if applicable for fees by checking the vehicleEntryTime
     *
     * @param vehicleDto  vehicleDto
     * @param parkingSlot parkingSlotDto
     */
    @Override
    public void applyFees(VehicleDto vehicleDto, ParkingSlot parkingSlot) {
        handleContinuousVehicle(vehicleDto);
        feeService.compute(vehicleDto, parkingSlot);
    }

    public void handleContinuousVehicle(VehicleDto vehicleDto) {
        if (TimeUtils.getDifferenceInHours(vehicleDto.getEntryTime(), vehicleDto.getExitTime())
                .compareTo(new BigDecimal(TimeConstants.MINUTES_HOURS)) >= 0) {
            vehicleDto.setContinuous(true);
        }
    }

    @Override
    public void updateExistingVehicle(VehicleDto vehicleDto, Vehicle existingVehicle) {
        vehicleDto.setReturnTime(LocalDateTime.now());
        vehicleMapper.updateVehicle(vehicleDto, existingVehicle, parkingSlotContext);
    }

    @Override
    public void updateParkingAndVehicle(VehicleDto vehicleDto, Vehicle parkedVehicle, ParkingSlot parkingSlot) {
        vehicleMapper.updateVehicle(vehicleDto, parkedVehicle, parkingSlotContext);
        parkingSlot.removeVehicle();
    }

}
