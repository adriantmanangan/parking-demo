package com.example.demo.rate.dto;

import java.math.BigDecimal;
import java.math.RoundingMode;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HourlyRate {

    private BigDecimal rate;

    /**
     * Computation of hour rate, checks if time is exceeds 3 hours and recalculate the remaining hours,
     * Hence, if below 3 hours, rate will be the flatRate.
     * @param minutes minutes
     * @param hourRate hourRate from parkingSlot size
     * @param flatRate flatRate from the configuration
     */
    public HourlyRate(double minutes, BigDecimal hourRate, BigDecimal flatRate) {
        if (minutes > 180) {
            rate = flatRate;
            this.rate = rate.add(BigDecimal.valueOf((minutes - 180) / 60)
                .setScale(0, RoundingMode.UP)
                .multiply(hourRate));
        } else {
            this.rate = flatRate;
        }
    }
}
