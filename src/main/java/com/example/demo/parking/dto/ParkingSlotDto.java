package com.example.demo.parking.dto;

import java.time.LocalDate;
import java.util.Date;

import com.example.demo.parking.Size;
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
