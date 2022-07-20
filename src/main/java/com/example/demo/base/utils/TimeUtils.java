package com.example.demo.base.utils;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;

import com.example.demo.fee.constants.FeeConstants;
import com.example.demo.vehicle.dto.VehicleDto;

public class TimeUtils {

    public static BigDecimal getDays(BigDecimal hour) {
        return BigDecimal.valueOf(hour.divide(new BigDecimal(FeeConstants.HOURS_DAYS)).intValue());
    }

    public static boolean isParkingTimeExceeds24Hrs(BigDecimal hours){
        return hours.compareTo(new BigDecimal(FeeConstants.HOURS_DAYS)) >= 0;
    }

    public static BigDecimal getHours(BigDecimal hours){
        return hours.remainder(new BigDecimal(FeeConstants.HOURS_DAYS));
    }

    public static BigDecimal getDifferenceInMinutes(LocalDateTime firstDate, LocalDateTime secondDate){
        return BigDecimal.valueOf(Duration.between(firstDate,secondDate).toMinutes());
    }

    public static BigDecimal getDifferenceInHours (LocalDateTime firstDate, LocalDateTime secondDate){
        return BigDecimal.valueOf(Duration.between(firstDate,secondDate).toHours());
    }

    public static boolean isExitDateLess1Hr(VehicleDto vehicleDto){
        return TimeUtils.getDifferenceInMinutes(vehicleDto.getEntryTime(),vehicleDto.getExitTime())
            .compareTo(new BigDecimal(FeeConstants.MINUTES_HOURS)) > 0;
    }
}
