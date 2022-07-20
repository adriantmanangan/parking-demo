package com.example.demo.parking.mapper;

import java.util.List;

import com.example.demo.base.converter.SizeEnumConverter;
import com.example.demo.parking.dto.ParkingDto;
import com.example.demo.parking.model.Parking;
import com.example.demo.parkingslot.mapper.ParkingSlotContext;
import com.example.demo.parkingslot.mapper.ParkingSlotMapper;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(
    componentModel = "spring",
    unmappedTargetPolicy = ReportingPolicy.IGNORE,
    uses = {SizeEnumConverter.class, ParkingSlotMapper.class}
)
public interface ParkingMapper {

    Parking mapToParking(ParkingDto parkingDto, @Context ParkingSlotContext parkingSlotContext);

    ParkingDto mapToParkingDto(Parking parking);

    List<Parking> mapToParkingList(List <ParkingDto> parkingDto, @Context ParkingSlotContext parkingSlotContext);

    List<ParkingDto> mapToParkingDtoList(List<Parking> parking);

}
