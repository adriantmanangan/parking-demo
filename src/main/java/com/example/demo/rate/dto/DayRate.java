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
     * @param minutes minutes
     * @param dayRate dayRate
     * @param hourRate hourRate
     */
    public DayRate(double minutes, BigDecimal dayRate, BigDecimal hourRate) {
        BigDecimal hoursByDay = FeeUtils.getHoursByDay(minutes);
        BigDecimal hour = FeeUtils.getRemainder(hoursByDay);
        if(hour.compareTo(BigDecimal.ZERO) > 0){
            hour = hour.multiply(hourRate);
        }
        BigDecimal day = dayRate.multiply(new BigDecimal(FeeUtils.getDay(hoursByDay)));
        this.rate = hour.add(day);
    }
}
