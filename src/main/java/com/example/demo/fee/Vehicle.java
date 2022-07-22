package com.example.demo.fee;

import java.math.BigDecimal;

import com.example.demo.parkingslot.dto.ParkingSlotDto;
import com.example.demo.parkingslot.model.ParkingSlot;
import com.example.demo.vehicle.dto.VehicleDto;

public interface Vehicle {

    boolean isSize(ParkingSlot parkingSlot);

    BigDecimal getHourlyRate(VehicleDto vehicleDto);

}
