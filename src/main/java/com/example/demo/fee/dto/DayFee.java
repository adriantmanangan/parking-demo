package com.example.demo.fee.dto;

import static com.example.demo.base.utils.TimeUtils.isParkingTimeExceeds24Hrs;

import java.math.BigDecimal;

import com.example.demo.base.utils.TimeUtils;
import com.example.demo.fee.Fee;
import com.example.demo.vehicle.dto.VehicleDto;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;

@Setter
@Getter
public class DayFee implements Fee {

    private BigDecimal rate;

    @Value("${parking.fee.exceeds}")
    private BigDecimal dayRate;

    /**
     * Computation by day rate + extra hours (if applicable).
     *
     * @param hours      hours of the vehicle
     * @param hourRate   hourRate of the parking slot
     * @param vehicleDto set fees for vehicle
     */
    @Override
    public void compute(BigDecimal hours, BigDecimal hourRate, VehicleDto vehicleDto) {
        BigDecimal day = TimeUtils.getDays(hours);
        BigDecimal hour = TimeUtils.getHours(hours);
        if (hour.compareTo(BigDecimal.ZERO) > 0) {
            hour = hour.multiply(hourRate);
        }
        BigDecimal totalRate = dayRate.multiply(day);
        this.rate = hour.add(totalRate);
        Fee.super.setVehicleFee(rate, vehicleDto);
    }

    @Override
    public boolean isApply(BigDecimal hours) {
        return isParkingTimeExceeds24Hrs(hours);
    }
}
