package com.example.demo.parkingslot.mapper;

import com.example.demo.base.converter.SizeEnumConverter;
import com.example.demo.parking.mapper.ParkingMapper;
import com.example.demo.parking.model.Parking;
import com.example.demo.parkingslot.dto.ParkingSlotDto;
import com.example.demo.parkingslot.model.ParkingSlot;
import org.mapstruct.*;

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

    @Mapping(target = "version", ignore = true)
    Parking mapParkingToParkingSlot(ParkingSlotDto parkingSlotDto, @MappingTarget Parking parking, @Context ParkingSlotContext parkingSlotContext);



}
