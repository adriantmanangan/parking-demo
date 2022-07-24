package com.example.demo.vehicle.dto;

import com.example.demo.base.constants.Size;
import com.example.demo.base.utils.DateUtils;
import com.example.demo.base.validator.EnumSizeValidation;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VehicleDto {

    @NotBlank(message = "vehicle.size.number.mandatory")
    private String plateNumber;

    @EnumSizeValidation(enumClass = Size.class, message = "Invalid Size")
    @NotBlank(message = "plate.number.mandatory")
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

    private Boolean isPark;

    @Builder.Default
    private boolean isContinuous = false;

}
