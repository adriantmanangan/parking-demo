package com.example.demo.base.exceptions;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.Errors;

@RequiredArgsConstructor
public class ValidationException extends RuntimeException{

    @Getter
    private final transient Errors errors;
}
