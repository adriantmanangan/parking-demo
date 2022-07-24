package com.example.demo.parking.dto;

import com.example.demo.parkingslot.dto.ParkingSlotDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ParkingDto {

    @NotBlank(message = "parking.number.mandatory")
    private String parkingNumber;

    @Builder.Default
    @Size(min = 3,message = "parking.slots.minimum")
    @Valid
    private List<ParkingSlotDto> parkingSlotList = new ArrayList<>();
}
