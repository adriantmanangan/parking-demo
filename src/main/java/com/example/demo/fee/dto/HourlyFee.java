package com.example.demo.fee.dto;

import static com.example.demo.base.utils.TimeUtils.isParkingTimeExceeds24Hrs;

import java.math.BigDecimal;

import com.example.demo.fee.Fee;
import com.example.demo.vehicle.dto.VehicleDto;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;

@Getter
@Setter
public class HourlyFee implements Fee {

    private BigDecimal rate;

    @Value("${parking.fee.flatRate}")
    private BigDecimal flatRate;


    public HourlyFee(BigDecimal hours, BigDecimal hourRate, BigDecimal flatRate) {
        if (hours.compareTo(new BigDecimal(3)) > 0) {
            rate = flatRate;
            this.rate = rate.add(hours.subtract(new BigDecimal(3)).multiply(hourRate));
        } else {
            this.rate = flatRate;
        }
    }

    /**
     * Computation of hour rate, checks if time is exceeds 3 hours and recalculate the remaining hours,
     * Hence, if below 3 hours, rate will be the flatRate.
     * @param hours minutes
     * @param hourRate hourRate from parkingSlot size
     */
    @Override
    public void compute(BigDecimal hours, BigDecimal hourRate, VehicleDto vehicleDto) {
        if (hours.compareTo(new BigDecimal(3)) > 0) {
            rate = flatRate;
            this.rate = rate.add(hours.subtract(new BigDecimal(3)).multiply(hourRate));
        } else {
            this.rate = flatRate;
        }
        Fee.super.setVehicleFee(rate, vehicleDto);
    }

    @Override
    public boolean isApply(BigDecimal hours) {
        return !isParkingTimeExceeds24Hrs(hours);
    }
}
