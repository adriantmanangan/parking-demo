package com.example.demo.parking.service.vehicle;

import java.util.Optional;

import com.example.demo.parking.constants.SuccessMessageResponse;
import com.example.demo.parking.dto.ParkingSlotDto;
import com.example.demo.parking.dto.VehicleDto;
import com.example.demo.parking.model.ParkingSlot;
import com.example.demo.parking.model.Vehicle;

public interface VehicleService {

    SuccessMessageResponse addToParkingSlot(VehicleDto vehicleDto);

    Optional<ParkingSlot> availableParkingSlot(VehicleDto vehicleDto);

    SuccessMessageResponse unparkVehicle(VehicleDto vehicleDto);

    void applyFees(VehicleDto vehicleDto, ParkingSlotDto parkingDto);

    void updateExistingVehicle(VehicleDto vehicleDto, Vehicle vehicle);

    void updateParkingAndVehicle(VehicleDto vehicleDto, Vehicle vehicle, ParkingSlot parkingSlot);


}
