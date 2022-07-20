package com.example.demo.fee.dto;

import java.math.BigDecimal;

import com.example.demo.base.utils.TimeUtils;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class DayFee {

    private BigDecimal rate;

    /**
     * Computation by day rate + extra hours (if applicable).
     * @param hours minutes
     * @param dayRate dayRate
     * @param hourRate hourRate
     */
    public DayFee(BigDecimal hours, BigDecimal dayRate, BigDecimal hourRate) {
        BigDecimal day = TimeUtils.getDays(hours);
        BigDecimal hour = TimeUtils.getHours(hours);
        if(hour.compareTo(BigDecimal.ZERO) > 0){
            hour = hour.multiply(hourRate);
        }
        BigDecimal totalRate = dayRate.multiply(day);
        this.rate = hour.add(totalRate);
    }
}
