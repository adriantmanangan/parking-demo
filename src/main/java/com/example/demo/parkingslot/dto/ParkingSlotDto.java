package com.example.demo.parkingslot.dto;

import java.util.Date;
import javax.validation.Valid;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;

import com.example.demo.base.constants.Size;
import com.example.demo.base.validator.EnumSizeValidation;
import com.example.demo.parking.dto.ParkingDto;
import com.example.demo.vehicle.dto.VehicleDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ParkingSlotDto {

    private String parkingSlotNumber;

    private Long distanceFromTheEntrance;

    @NotNull(message = "parking.slot.size.mandatory")
    @EnumSizeValidation(enumClass = Size.class, message = "Invalid Size")
    private String size;

    @JsonIgnore
    @Builder.Default
    private Boolean isAvailable = true;

    @JsonIgnore
    private Date dateUpdated;

    @JsonIgnore
    private Date dateCreated;

    @JsonIgnore
    private ParkingDto parkingDto;

    @JsonIgnore
    private VehicleDto vehicleDto;


}
