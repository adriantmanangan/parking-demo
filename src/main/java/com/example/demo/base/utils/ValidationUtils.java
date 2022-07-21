package com.example.demo.base.utils;

import com.example.demo.base.exceptions.ValidationException;
import org.springframework.validation.Errors;

public class ValidationUtils {

    private ValidationUtils() {
    }

    public static void rejectParkingSlot(Errors errors, String fields, String code) {
        errors.rejectValue(fields, code);
        throw new ValidationException(errors);
    }
}
