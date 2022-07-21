package com.example.demo.fee.dto;

import java.math.BigDecimal;

import com.example.demo.base.constants.Size;
import com.example.demo.parking.fee.FeeCalculator;
import com.example.demo.parkingslot.dto.ParkingSlotDto;
import com.example.demo.vehicle.dto.VehicleDto;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
public class LargeCarFee implements FeeCalculator {

    @Value("${parking.fee.large}")
    private BigDecimal hourlyRate;

    @Override
    public boolean isSize(ParkingSlotDto parkingSlotDto) {
        return parkingSlotDto.getSize() == Size.LARGE;
    }

    @Override
    public BigDecimal getHourlyRate(VehicleDto vehicleDto) {
        return getHourlyRate();
    }
}