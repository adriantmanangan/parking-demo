package com.example.demo.base.service.response;

import java.util.List;

import com.example.demo.base.ApiErrorCode;
import com.example.demo.base.ErrorResponseItem;
import com.example.demo.parkingslot.advice.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ErrorResponseService {

    public ErrorResponse multipleErrors(HttpStatus httpStatus, List<ErrorResponseItem> errorResponseItems) {
        log.debug("Creating error response for http status {}", httpStatus);
        return new ErrorResponse(httpStatus.value(), httpStatus.getReasonPhrase(), errorResponseItems);
    }

    public ErrorResponse singleError(HttpStatus httpStatus, ApiErrorCode apiErrorCode, String message) {
        return singleError(httpStatus, new ErrorResponseItem(apiErrorCode, message));
    }

    public ErrorResponse singleError(HttpStatus httpStatus, ErrorResponseItem errorResponseItem) {
        return multipleErrors(httpStatus, List.of(errorResponseItem));
    }
}
