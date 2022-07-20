package com.example.demo.parking.repository;

import com.example.demo.parking.model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VehicleRepository extends JpaRepository<Vehicle, Long> {

    Vehicle findByPlateNumberAndIsPark(String plateNumber, boolean isParked);

}
