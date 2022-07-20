package com.example.demo.parking.dto;

import java.util.ArrayList;
import java.util.List;

import com.example.demo.parking.model.ParkingSlot;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ParkingDto {

    private String parkingNumber;

    @Builder.Default
    private List<ParkingSlotDto> parkingSlotList = new ArrayList<>();
}
