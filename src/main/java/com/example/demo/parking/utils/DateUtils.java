package com.example.demo.parking.utils;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;

public class DateUtils {

    public static final String DATE_PATTERN = "dd/MM/yyyy";

    public static final String DATE_TIME_PATTERN = "yyyy-MM-dd@HH:mm:ss";

    private DateUtils() {

    }

    public static double getDifferenceInMinutes(LocalDateTime firstDate, LocalDateTime secondDate){
        Duration duration = Duration.between(firstDate,secondDate);
        return Math.abs(duration.toMinutes());
    }

    public static BigDecimal getDifferenceInHours (LocalDateTime firstDate, LocalDateTime secondDate){
        return BigDecimal.valueOf(Duration.between(firstDate,secondDate).toHours());
    }

}
