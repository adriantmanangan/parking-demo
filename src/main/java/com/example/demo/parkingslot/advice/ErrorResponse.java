package com.example.demo.parkingslot.advice;

import com.example.demo.base.ErrorResponseItem;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.collect.ImmutableList;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.List;

@Data
@ToString
@EqualsAndHashCode
public class ErrorResponse {
    @JsonProperty("status_code")
    private final Integer statusCode;

    @JsonProperty("status_message")
    private final String statusMessage;

    private final List<ErrorResponseItem> errors;

    /**
     * Full arg constructor.
     *
     * @param statusCode http status code
     * @param statusMessage http status message
     * @param errors list of error response items
     */
    @JsonCreator
    public ErrorResponse(@JsonProperty("status_code") Integer statusCode,
                         @JsonProperty("status_message") String statusMessage,
                         @JsonProperty("errors") List<ErrorResponseItem> errors) {

        this.statusCode = statusCode;
        this.statusMessage = statusMessage;
        this.errors = ImmutableList.copyOf(errors);
    }
}
