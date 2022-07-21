package com.example.demo.parkingslot.service.validation;

import com.example.demo.base.exceptions.ValidationException;
import com.example.demo.parkingslot.dto.ParkingSlotDto;
import org.springframework.validation.Errors;

public interface ParkingSlotValidationService {

    void validateParkingSlot(ParkingSlotDto parkingSlotDto, Errors errors) throws ValidationException;
}
