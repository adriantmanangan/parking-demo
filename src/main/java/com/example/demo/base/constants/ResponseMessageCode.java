package com.example.demo.base.constants;

public enum  ResponseMessageCode {

    SUCCESS_PARK("vehicle.park.success"),
    SUCCESS_UNPARK("vehicle.unpark.success"),
    SUCCESS_CREATE_PARKING("parking.create.success"),
    SUCCESS_VALIDATION("parking.create.success"),

    VEHICLE_VALIDATION_NO_ERRORS("vehicle.validation.no.errors");

    private final String msgCode;

    ResponseMessageCode(String msgCode) {
        this.msgCode = msgCode;
    }

    public String getMsgCode() {
        return msgCode;
    }
}
