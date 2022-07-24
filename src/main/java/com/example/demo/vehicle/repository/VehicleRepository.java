package com.example.demo.vehicle.repository;

import com.example.demo.vehicle.model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VehicleRepository extends JpaRepository<Vehicle, Long> {

    Optional<Vehicle> findByPlateNumberAndIsPark(String plateNumber, boolean isParked);

}
