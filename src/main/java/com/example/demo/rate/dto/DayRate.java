package com.example.demo.rate.dto;

import java.math.BigDecimal;

import com.example.demo.parking.utils.FeeUtils;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class DayRate {

    private BigDecimal rate;

    /**
     * Computation by day rate + extra hours (if applicable).
     * @param hours minutes
     * @param dayRate dayRate
     * @param hourRate hourRate
     */
    public DayRate(BigDecimal hours, BigDecimal dayRate, BigDecimal hourRate) {
        BigDecimal day = FeeUtils.getDays(hours);
        BigDecimal hour = FeeUtils.getHours(hours);
        if(hour.compareTo(BigDecimal.ZERO) > 0){
            hour = hour.multiply(hourRate);
        }
        BigDecimal totalRate = dayRate.multiply(day);
        this.rate = hour.add(totalRate);
    }
}
