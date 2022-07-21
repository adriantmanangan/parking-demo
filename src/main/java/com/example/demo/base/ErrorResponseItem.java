package com.example.demo.base;

import static com.google.common.base.Strings.nullToEmpty;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString
@EqualsAndHashCode
public class ErrorResponseItem implements Serializable {
    private final ApiErrorCode code;
    private final String message;
    private final String field;

    public ErrorResponseItem(ApiErrorCode code, String message) {
        this(code, message, "");
    }

    @JsonCreator
    public ErrorResponseItem(@JsonProperty("code") ApiErrorCode code,
                             @JsonProperty("message") String message,
                             @JsonProperty("field") String field) {

        this.code = code;
        this.message = message;
        this.field = nullToEmpty(field);
    }
}

