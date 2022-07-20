package com.example.demo.vehicle.mapper;

import com.example.demo.base.converter.SizeEnumConverter;
import com.example.demo.parkingslot.mapper.ParkingSlotContext;
import com.example.demo.vehicle.dto.VehicleDto;
import com.example.demo.vehicle.model.Vehicle;
import org.mapstruct.BeanMapping;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(
    unmappedTargetPolicy = ReportingPolicy.IGNORE,
    componentModel = "spring",
    uses = SizeEnumConverter.class)
public interface VehicleMapper {

    @Mapping(source = "size",target = "size",qualifiedByName = {"enumSizeToInteger"})
    Vehicle mapToVehicle(VehicleDto vehicleDto, @Context ParkingSlotContext parkingSlotContext);

    @Mapping(source = "size",target = "size",qualifiedByName = {"integerToEnumSize"})
    VehicleDto mapToVehicleDto(Vehicle vehicle);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
    @Mapping(source = "size",target = "size",qualifiedByName = {"integerToEnumSize"})
    VehicleDto updateVehicleDto(Vehicle vehicle, @MappingTarget VehicleDto vehicleDto,@Context ParkingSlotContext parkingSlotContext);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
    @Mapping(source = "size",target = "size",qualifiedByName = {"enumSizeToInteger"})


    Vehicle updateVehicle(VehicleDto vehicleDto, @MappingTarget Vehicle vehicle,@Context ParkingSlotContext parkingSlotContext);

}
