package com.example.demo.vehicle.service;

import java.util.Optional;

import com.example.demo.base.constants.SuccessMessageResponse;
import com.example.demo.parkingslot.dto.ParkingSlotDto;
import com.example.demo.parkingslot.model.ParkingSlot;
import com.example.demo.vehicle.dto.VehicleDto;
import com.example.demo.vehicle.model.Vehicle;

public interface VehicleService {

    SuccessMessageResponse addToParkingSlot(VehicleDto vehicleDto);

    SuccessMessageResponse updateToParkingSlot(VehicleDto vehicleDto,Vehicle existingVehicle);

    SuccessMessageResponse parkVehicle(VehicleDto vehicleDto);

    Optional<ParkingSlot> availableParkingSlot(VehicleDto vehicleDto);

    SuccessMessageResponse unparkVehicle(VehicleDto vehicleDto);

    void applyFees(VehicleDto vehicleDto, ParkingSlot parkingSlot);

    void updateExistingVehicle(VehicleDto vehicleDto, Vehicle vehicle);

    void updateParkingAndVehicle(VehicleDto vehicleDto, Vehicle vehicle, ParkingSlot parkingSlot);


}
