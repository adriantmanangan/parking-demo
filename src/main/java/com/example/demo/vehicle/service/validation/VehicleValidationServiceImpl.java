package com.example.demo.vehicle.service.validation;

import com.example.demo.base.utils.ValidationUtils;
import com.example.demo.vehicle.dto.VehicleDto;
import com.example.demo.vehicle.model.Vehicle;
import com.example.demo.vehicle.repository.VehicleRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;

import java.util.Optional;

@Service
@AllArgsConstructor
public class VehicleValidationServiceImpl implements VehicleValidationService {

    private final VehicleRepository vehicleRepository;
    @Override
    public void validate(VehicleDto vehicleDto, Errors errors) {
        Optional<Vehicle> existingVehicle = vehicleRepository.findByPlateNumberAndIsPark(vehicleDto.getPlateNumber(), true);
        existingVehicle.ifPresentOrElse(ValidationUtils.reject(errors,"plateNumber","vehicle.exist"),() -> ResponseEntity.accepted());
    }
}
