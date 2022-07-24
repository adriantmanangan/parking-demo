package com.example.demo.fee;

import com.example.demo.vehicle.dto.VehicleDto;

import java.math.BigDecimal;

public interface Fee {

    void compute(BigDecimal hours, BigDecimal hourRate, VehicleDto vehicleDto);

    boolean isApply(BigDecimal hours);

    default void setVehicleFee(BigDecimal rate, VehicleDto vehicleDto){
        vehicleDto.setFee(rate);
    }
}
