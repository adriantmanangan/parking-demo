package com.example.demo.rate.dto;

import java.math.BigDecimal;

import com.example.demo.base.constants.Size;
import com.example.demo.parking.fee.FeeCalculator;
import com.example.demo.parkingslot.dto.ParkingSlotDto;
import com.example.demo.vehicle.dto.VehicleDto;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Component
public class SmallCarFee implements FeeCalculator {

    @Value("${parking.fee.small}")
    private BigDecimal hourlyRate;

    @Override
    public boolean isSize(ParkingSlotDto parkingSlotDto) {
        return parkingSlotDto.getSize() == Size.SMALL;
    }

    @Override
    public BigDecimal getHourlyRate(VehicleDto vehicleDto) {
        return getHourlyRate();
    }
}
