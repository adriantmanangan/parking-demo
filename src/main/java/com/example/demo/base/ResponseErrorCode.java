package com.example.demo.base;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ResponseErrorCode implements ApiErrorCode {

    VALIDATION_ERROR(HttpStatus.BAD_REQUEST, ErrorType.VALIDATION, "validation.error"),
    PARKING_NOT_FOUND(HttpStatus.BAD_REQUEST, ErrorType.PARKING, "parking.not.found ");

    private final HttpStatus status;
    private final String msgCode;
    private final String code;

    ResponseErrorCode(HttpStatus status, ErrorType type, String msgCode) {
        this.status = status;
        this.code = type.name() + "_ERROR";
        this.msgCode = msgCode;
    }

    private enum ErrorType {
        VALIDATION, PARKING
    }
}
