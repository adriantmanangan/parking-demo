package com.example.demo.parking.service.fee;

import java.math.BigDecimal;

import com.example.demo.parking.Size;
import com.example.demo.parking.dto.ParkingSlotDto;
import com.example.demo.parking.dto.VehicleDto;
import com.example.demo.parking.fee.FeeCalculator;
import lombok.AllArgsConstructor;
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
