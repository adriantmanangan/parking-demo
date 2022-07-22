package com.example.demo.vehicle.dto;

import com.example.demo.base.constants.Size;
import com.example.demo.base.utils.DateUtils;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VehicleUnparkDto {

    @NotBlank(message = "vehicle.size.number.mandatory")
    private String plateNumber;

    @NotBlank(message = "plate.number.mandatory")
    private Size size;

    @NotBlank(message = "exit.time.mandatory")
    @JsonFormat(pattern = DateUtils.DATE_TIME_PATTERN, shape = JsonFormat.Shape.STRING)
    private LocalDateTime exitTime;


}
