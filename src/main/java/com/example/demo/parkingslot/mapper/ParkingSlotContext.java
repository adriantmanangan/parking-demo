package com.example.demo.parkingslot.mapper;

import com.example.demo.parking.model.Parking;
import com.example.demo.parkingslot.model.ParkingSlot;
import com.example.demo.vehicle.model.Vehicle;
import org.mapstruct.AfterMapping;
import org.mapstruct.BeforeMapping;
import org.mapstruct.MappingTarget;
import org.springframework.stereotype.Component;

@Component
public class ParkingSlotContext {

    private ParkingSlot parkingSlot;

    private Parking parking;

    @BeforeMapping
    public void setParkingSlot(@MappingTarget ParkingSlot parkingSlot) {
        this.parkingSlot = parkingSlot;
        parkingSlot.setParking(parking);
    }

    @AfterMapping
    public void setVehicleParkingSlot(@MappingTarget Vehicle vehicle){
        vehicle.setParkingSlot(parkingSlot);
        vehicle.setIsPark(true);
        parkingSlot.setVehicle(vehicle);
    }

    @BeforeMapping
    public void setParking(@MappingTarget Parking parking) {
        this.parking = parking;
    }


}
