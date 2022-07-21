package com.example.demo.base;

import com.fasterxml.jackson.annotation.JsonValue;

public interface ApiErrorCode {
    @JsonValue
    String getCode();
}