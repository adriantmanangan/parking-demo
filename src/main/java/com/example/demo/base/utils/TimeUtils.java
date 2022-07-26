package com.example.demo.base.utils;

import com.example.demo.fee.constants.TimeConstants;
import com.example.demo.vehicle.dto.VehicleDto;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;

public class TimeUtils {

    public static BigDecimal getDays(BigDecimal hour) {
        return BigDecimal.valueOf(hour.divide(new BigDecimal(TimeConstants.HOURS_DAYS)).intValue());
    }

    public static boolean isParkingTimeExceeds24Hrs(BigDecimal hours){
        return hours.compareTo(new BigDecimal(TimeConstants.HOURS_DAYS)) >= 0;
    }

    public static BigDecimal getHours(BigDecimal hours){
        return hours.remainder(new BigDecimal(TimeConstants.HOURS_DAYS));
    }

    public static BigDecimal getDifferenceInMinutes(LocalDateTime firstDate, LocalDateTime secondDate){
        return BigDecimal.valueOf(Duration.between(firstDate,secondDate).toMinutes());
    }

    public static BigDecimal getDifferenceInHours (LocalDateTime firstDate, LocalDateTime secondDate){
        return BigDecimal.valueOf(Duration.between(firstDate,secondDate).toHours());
    }

    public static boolean isExitDateLess1Hr(VehicleDto vehicleDto){
        return TimeUtils.getDifferenceInMinutes(vehicleDto.getEntryTime(),vehicleDto.getExitTime())
            .compareTo(new BigDecimal(TimeConstants.MINUTES_HOURS)) > 0;
    }
}
