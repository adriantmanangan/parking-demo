package com.example.demo.parkingslot.dto;

import java.util.Date;

import com.example.demo.base.constants.Size;
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

    private Long distanceFromTheEntrance;

    private Size size;

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
