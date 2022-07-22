package com.example.demo.fee.dto;

import java.math.BigDecimal;
import java.util.Objects;

import com.example.demo.base.constants.Size;
import com.example.demo.fee.Vehicle;
import com.example.demo.parkingslot.model.ParkingSlot;
import com.example.demo.vehicle.dto.VehicleDto;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Component
public class MediumCarFee implements Vehicle {

    @Value("${parking.fee.medium}")
    private BigDecimal hourlyRate;

    @Override
    public boolean isSize(ParkingSlot parkingSlot) {
        return Objects.equals(parkingSlot.getSize(), Size.MEDIUM.getValue());
    }

    @Override
    public BigDecimal getHourlyRate(VehicleDto vehicleDto) {
        return getHourlyRate();
    }

}
