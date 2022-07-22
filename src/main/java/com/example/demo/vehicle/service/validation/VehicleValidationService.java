package com.example.demo.vehicle.service.validation;

import com.example.demo.vehicle.dto.VehicleDto;
import org.springframework.validation.Errors;

public interface VehicleValidationService {

    void validate(VehicleDto vehicleDto, Errors errors);
}
