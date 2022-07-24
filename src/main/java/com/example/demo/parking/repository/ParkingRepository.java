package com.example.demo.parking.repository;

import com.example.demo.parking.model.Parking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ParkingRepository extends JpaRepository<Parking, Long> {

   Optional<Parking> findByParkingNumber(String reference);
}
