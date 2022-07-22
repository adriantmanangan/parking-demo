package com.example.demo.fee;

import java.math.BigDecimal;

import com.example.demo.vehicle.dto.VehicleDto;

public interface Fee {

    void compute(BigDecimal hours, BigDecimal hourRate, VehicleDto vehicleDto);

    boolean isApply(BigDecimal hours);

    default void setVehicleFee(BigDecimal rate, VehicleDto vehicleDto){
        vehicleDto.setFee(rate);
    }
}
