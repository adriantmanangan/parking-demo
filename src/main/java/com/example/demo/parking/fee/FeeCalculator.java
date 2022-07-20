package com.example.demo.parking.fee;

import java.math.BigDecimal;

import com.example.demo.parkingslot.dto.ParkingSlotDto;
import com.example.demo.vehicle.dto.VehicleDto;

public interface FeeCalculator {

    boolean isSize(ParkingSlotDto parkingSlotDto);

    BigDecimal getHourlyRate(VehicleDto vehicleDto);

}
