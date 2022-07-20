package com.example.demo.parking.repository;

import com.example.demo.parking.model.Parking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParkingRepository extends JpaRepository<Parking, Long> {

    Parking findByParkingNumber(String reference);
}
