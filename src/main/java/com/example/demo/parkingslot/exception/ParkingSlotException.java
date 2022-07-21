package com.example.demo.parkingslot.exception;

import com.example.demo.base.ResponseErrorCode;
import lombok.Getter;

@Getter
public class ParkingSlotException extends RuntimeException{

    private final ResponseErrorCode responseErrorCode;

    private final transient Object[] errorArguments;

    public ParkingSlotException(final ResponseErrorCode responseErrorCode, final Object... errorArguments) {
        super(responseErrorCode.toString());
        this.responseErrorCode = responseErrorCode;
        this.errorArguments = errorArguments;
    }
}
