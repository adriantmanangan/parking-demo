package com.example.demo.vehicle.controller.validation;

import com.example.demo.base.constants.ResponseMessageCode;
import com.example.demo.base.constants.SuccessMessageResponse;
import com.example.demo.base.service.response.ResponseService;
import com.example.demo.vehicle.dto.VehicleDto;
import com.example.demo.vehicle.service.validation.VehicleValidationService;
import lombok.AllArgsConstructor;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class VehicleValidationController {

    private final VehicleValidationService vehicleValidationService;
    private final ResponseService responseService;

    @PostMapping("/api/vehicle/validate")
    public SuccessMessageResponse parkVehicle(@RequestBody VehicleDto vehicleDto, Errors errors) {
        vehicleValidationService.validate(vehicleDto, errors);
        return responseService.createSuccessfulMessageResponse(ResponseMessageCode.VEHICLE_VALIDATION_NO_ERRORS);
    }

}
