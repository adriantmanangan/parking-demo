package com.example.demo.vehicle.repository;

import java.util.Optional;

import com.example.demo.vehicle.model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VehicleRepository extends JpaRepository<Vehicle, Long> {

    Optional<Vehicle> findByPlateNumberAndIsPark(String plateNumber, boolean isParked);

}
