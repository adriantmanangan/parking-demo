package com.example.demo.fee.dto;

import com.example.demo.base.constants.Size;
import com.example.demo.fee.Vehicle;
import com.example.demo.parkingslot.model.ParkingSlot;
import com.example.demo.vehicle.dto.VehicleDto;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Objects;

@Getter
@Component
public class SmallCarFee implements Vehicle {

    @Value("${parking.fee.small}")
    private BigDecimal hourlyRate;

    @Override
    public boolean isSize(ParkingSlot parkingSlot) {
        return Objects.equals(parkingSlot.getSize(), Size.SMALL.getValue());
    }

    @Override
    public BigDecimal getHourlyRate(VehicleDto vehicleDto) {
        return getHourlyRate();
    }
}
