package com.example.demo.parking.mapper;

import com.example.demo.parking.converter.SizeEnumConverter;
import com.example.demo.parking.dto.ParkingSlotDto;
import com.example.demo.parking.model.ParkingSlot;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(
    unmappedTargetPolicy = ReportingPolicy.IGNORE,
    componentModel = "spring",
    uses = {SizeEnumConverter.class, ParkingMapper.class}
)
public interface ParkingSlotMapper {

    @Mapping(source = "size", target = "size", qualifiedByName = {"integerToEnumSize"})
    ParkingSlotDto mapToParkingSlotDto(ParkingSlot parkingSlot);

    @Mapping(source = "size", target = "size", qualifiedByName = {"enumSizeToInteger"})
    @Mapping(target = "version", ignore = true)
    ParkingSlot mapToParkingSlot(ParkingSlotDto parkingSlotDto, @Context ParkingSlotContext parkingSlotContext);

}
