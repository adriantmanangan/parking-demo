package com.example.demo.base.validator.validation;

import com.example.demo.base.validator.EnumSizeValidation;
import lombok.AllArgsConstructor;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@AllArgsConstructor
public class EnumSizeValidator implements ConstraintValidator<EnumSizeValidation, String> {

    private List<String> acceptedValues;

    @Override
    public void initialize(EnumSizeValidation annotation) {
        acceptedValues = Stream.of(annotation.enumClass().getEnumConstants()).map(Enum::name).collect(Collectors.toList());
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }
        return acceptedValues.contains(value);
    }
}
