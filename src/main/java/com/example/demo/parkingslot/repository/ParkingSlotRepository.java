package com.example.demo.parkingslot.repository;

import java.util.List;
import java.util.Optional;

import com.example.demo.parking.model.Parking;
import com.example.demo.parkingslot.model.ParkingSlot;
import com.example.demo.vehicle.model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParkingSlotRepository extends JpaRepository<ParkingSlot, Long> {

    Optional<List<ParkingSlot>> findAllByIsAvailableAndSizeAndParking(Boolean isAvailable, Integer vehicleSize, Parking parking);

    Optional<ParkingSlot> findByVehicle(Vehicle vehicle);

    Optional<List<ParkingSlot>> findByParking(Parking parking);

}
