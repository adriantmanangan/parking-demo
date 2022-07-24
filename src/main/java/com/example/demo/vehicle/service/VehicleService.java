package com.example.demo.vehicle.service;

import java.util.Optional;

import com.example.demo.base.constants.SuccessMessageResponse;
import com.example.demo.parkingslot.dto.ParkingSlotDto;
import com.example.demo.parkingslot.model.ParkingSlot;
import com.example.demo.vehicle.dto.VehicleDto;
import com.example.demo.vehicle.dto.VehicleUnparkDto;
import com.example.demo.vehicle.model.Vehicle;
import org.springframework.http.ResponseEntity;

public interface VehicleService {

    SuccessMessageResponse addToParkingSlot(VehicleDto vehicleDto, String reference);

    SuccessMessageResponse updateToParkingSlot(VehicleDto vehicleDto,Vehicle existingVehicle, String reference);

    SuccessMessageResponse parkVehicle(VehicleDto vehicleDto, String parkingNumber);

    SuccessMessageResponse unparkVehicle(VehicleUnparkDto vehicleDto, String parkingNumber);

    void applyFees(VehicleDto vehicleDto, ParkingSlot parkingSlot);

    void updateExistingVehicle(VehicleDto vehicleDto, Vehicle vehicle);

    void updateParkingAndVehicle(VehicleDto vehicleDto, Vehicle vehicle, ParkingSlot parkingSlot);



}
