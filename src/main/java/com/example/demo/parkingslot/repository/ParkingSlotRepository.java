package com.example.demo.parkingslot.repository;

import java.util.List;
import java.util.Optional;

import com.example.demo.parkingslot.model.ParkingSlot;
import com.example.demo.vehicle.model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParkingSlotRepository extends JpaRepository<ParkingSlot, Long> {

    Optional<List<ParkingSlot>> findAllByIsAvailableAndSize(Boolean isAvailable, Integer vehicleSize);

    Optional<ParkingSlot> findByVehicle(Vehicle vehicle);

}
