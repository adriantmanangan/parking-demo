package com.example.demo.vehicle.repository;

import com.example.demo.vehicle.model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VehicleRepository extends JpaRepository<Vehicle, Long> {

    Vehicle findByPlateNumberAndIsPark(String plateNumber, boolean isParked);

}
