package com.example.demo.base.utils;

import com.example.demo.base.exceptions.ValidationException;
import com.example.demo.vehicle.model.Vehicle;
import org.springframework.validation.Errors;

import java.util.function.Consumer;

public class ValidationUtils {

    private ValidationUtils() {
    }

    public static Consumer<? super Vehicle> reject(Errors errors, String fields, String code) {
        errors.rejectValue(fields, code);
        throw new ValidationException(errors);
    }
}
