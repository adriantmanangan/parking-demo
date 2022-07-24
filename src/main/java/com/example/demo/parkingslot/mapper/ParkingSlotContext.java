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

    private Parking parking;
    private ParkingSlot parkingSlot;

    @BeforeMapping
    public void setParking(@MappingTarget Parking parking) {
        this.parking = parking;
    }


    @AfterMapping
    public void setParkingSlotParking(@MappingTarget ParkingSlot parkingSlot){
        this.parkingSlot = parkingSlot;
        parkingSlot.setParking(parking);
    }

    @AfterMapping
    public void setVehicleParkingSlot(@MappingTarget Vehicle vehicle){
        vehicle.setParkingSlot(parkingSlot);
        parkingSlot.setVehicle(vehicle);
    }

    public void setParkingAndParkingSlot(Parking parking, ParkingSlot parkingSlot){
        setParking(parking);
        setParkingSlotParking(parkingSlot);
    }

}
