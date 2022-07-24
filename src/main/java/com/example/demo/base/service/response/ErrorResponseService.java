package com.example.demo.base.service.response;

import com.example.demo.base.ApiErrorCode;
import com.example.demo.base.ErrorResponseItem;
import com.example.demo.parkingslot.advice.ErrorResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;

import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class ErrorResponseService {

    private final ErrorResponseItemService errorResponseItemService;

    public ErrorResponse multipleErrors(HttpStatus httpStatus, List<ErrorResponseItem> errorResponseItems) {
        log.debug("Creating error response for http status {}", httpStatus);
        return new ErrorResponse(httpStatus.value(), httpStatus.getReasonPhrase(), errorResponseItems);
    }

    public ErrorResponse multipleErrors(HttpStatus httpStatus, ApiErrorCode apiErrorCode, Errors errors) {
        return multipleErrors(httpStatus, errorResponseItemService.toErrorResponseItems(apiErrorCode, errors));
    }

    public ErrorResponse singleError(HttpStatus httpStatus, ApiErrorCode apiErrorCode, String message) {
        return singleError(httpStatus, new ErrorResponseItem(apiErrorCode, message));
    }

    public ErrorResponse singleError(HttpStatus httpStatus, ErrorResponseItem errorResponseItem) {
        return multipleErrors(httpStatus, List.of(errorResponseItem));
    }
}
