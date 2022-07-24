package com.example.demo.fee;

import com.example.demo.parkingslot.model.ParkingSlot;
import com.example.demo.vehicle.dto.VehicleDto;

import java.math.BigDecimal;

public interface Vehicle {

    boolean isSize(ParkingSlot parkingSlot);

    BigDecimal getHourlyRate(VehicleDto vehicleDto);

}
