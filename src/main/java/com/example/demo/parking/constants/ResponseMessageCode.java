package com.example.demo.parking.constants;

public enum  ResponseMessageCode {

    SUCCESS_PARK("vehicle.park.success"),
    SUCCESS_UNPARK("vehicle.unpark.success");

    private final String msgCode;

    ResponseMessageCode(String msgCode) {
        this.msgCode = msgCode;
    }

    public String getMsgCode() {
        return msgCode;
    }
}
