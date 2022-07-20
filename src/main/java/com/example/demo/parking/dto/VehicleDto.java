package com.example.demo.parking.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.example.demo.parking.Size;
import com.example.demo.parking.utils.DateUtils;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VehicleDto {

    private String plateNumber;

    private Size size;

    @Builder.Default
    @JsonIgnore
    @JsonFormat(pattern = DateUtils.DATE_TIME_PATTERN, shape = JsonFormat.Shape.STRING)
    private LocalDateTime entryTime = LocalDateTime.now();

    @JsonFormat(pattern = DateUtils.DATE_TIME_PATTERN, shape = JsonFormat.Shape.STRING)
    private LocalDateTime exitTime;

    @JsonFormat(pattern = DateUtils.DATE_TIME_PATTERN, shape = JsonFormat.Shape.STRING)
    private LocalDateTime returnTime;

    private BigDecimal fee;

    @Builder.Default
    private boolean isPark= false;

}
