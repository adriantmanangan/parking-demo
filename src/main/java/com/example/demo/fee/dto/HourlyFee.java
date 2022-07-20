package com.example.demo.fee.dto;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HourlyFee {

    private BigDecimal rate;

    /**
     * Computation of hour rate, checks if time is exceeds 3 hours and recalculate the remaining hours,
     * Hence, if below 3 hours, rate will be the flatRate.
     * @param hours minutes
     * @param hourRate hourRate from parkingSlot size
     * @param flatRate flatRate from the configuration
     */
    public HourlyFee(BigDecimal hours, BigDecimal hourRate, BigDecimal flatRate) {
        if (hours.compareTo(new BigDecimal(3)) > 0) {
            rate = flatRate;
            this.rate = rate.add(hours.subtract(new BigDecimal(3)).multiply(hourRate));
        } else {
            this.rate = flatRate;
        }
    }
}
