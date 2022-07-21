package com.example.demo.parking.exception;

import com.example.demo.base.ResponseErrorCode;
import lombok.Getter;

@Getter
public class ParkingException extends RuntimeException {
    private final ResponseErrorCode responseErrorCode;

    private final transient Object[] errorArguments;

    public ParkingException(final ResponseErrorCode responseErrorCode, final Object... errorArguments) {
        this.responseErrorCode = responseErrorCode;
        this.errorArguments = errorArguments;
    }
}
