package com.example.demo.parkingslot.service.validation;

import com.example.demo.base.exceptions.ValidationException;
import com.example.demo.base.utils.ValidationUtils;
import com.example.demo.parkingslot.dto.ParkingSlotDto;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;

@Service
public class ParkingSlotValidationServiceImpl implements ParkingSlotValidationService {

    @Override
    public void validateParkingSlot(ParkingSlotDto parkingSlotDto, Errors errors) throws ValidationException {
        if (parkingSlotDto.getParkingDto().getParkingNumber() == null) {
            ValidationUtils.reject(errors, "parkingDto", "NotNull.parkingDto");
        }
    }
}
