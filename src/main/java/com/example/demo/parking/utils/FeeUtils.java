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

    public static BigDecimal getHoursByDay(double minutes) {
        return BigDecimal.valueOf(minutes / 1440);
    }

    public static boolean isParkingTimeExceeds24Hrs(double minutes){
        return minutes >= 1440;
    }

    public static  BigInteger getDay(BigDecimal hours) {
        return hours.toBigInteger();

    }

    public static BigDecimal getRemainder(BigDecimal hours){
        return hours.remainder(BigDecimal.ONE)
            .setScale(1, RoundingMode.UP)
            .multiply(new BigDecimal(10));
    }
}
