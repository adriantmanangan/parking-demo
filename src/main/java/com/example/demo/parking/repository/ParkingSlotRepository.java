package com.example.demo.parking.repository;

import java.util.List;
import java.util.Optional;

import com.example.demo.parking.model.ParkingSlot;
import com.example.demo.parking.model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParkingSlotRepository extends JpaRepository<ParkingSlot, Long> {

    Optional<List<ParkingSlot>> findAllByIsAvailableAndSize(Boolean isAvailable, Integer vehicleSize);

    Optional<ParkingSlot> findByVehicle(Vehicle vehicle);

}
