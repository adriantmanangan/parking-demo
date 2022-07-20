package com.example.demo.parking.utils;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;

public class FeeUtils {

    public static BigDecimal multiplyByRate(BigDecimal hours, BigDecimal rate) {
        BigDecimal fee;
        fee = hours.multiply(rate);
        return fee;
    }

    public static BigDecimal getDays(BigDecimal hour) {
        return BigDecimal.valueOf(hour.divide(new BigDecimal(24)).intValue());
    }

    public static boolean isParkingTimeExceeds24Hrs(BigDecimal hours){
        return hours.compareTo(new BigDecimal(24)) >= 0;

    }

    public static BigDecimal getHours(BigDecimal hours){
        return hours.remainder(new BigDecimal(24));
    }
}
