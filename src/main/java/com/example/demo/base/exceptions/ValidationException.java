package com.example.demo.base.exceptions;

import java.util.ArrayList;
import java.util.List;

import com.example.demo.base.ErrorResponseItem;
import com.google.common.collect.ImmutableList;

public class ValidationException extends RuntimeException {
    private final List<ErrorResponseItem> errorResponseItems = new ArrayList<>();

    private ValidationException() {
        super(null, null, true, false);
    }

    public ValidationException(List<ErrorResponseItem> errorResponseItems) {
        this();
        this.errorResponseItems.addAll(errorResponseItems);
    }

    public ValidationException(ErrorResponseItem errorResponseItem) {
        this();
        this.errorResponseItems.add(errorResponseItem);
    }

    public List<ErrorResponseItem> getErrorResponseItems() {
        return ImmutableList.copyOf(errorResponseItems);
    }
}
