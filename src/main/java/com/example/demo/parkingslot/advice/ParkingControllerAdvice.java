package com.example.demo.parkingslot.advice;

import com.example.demo.base.exceptions.BaseException;
import com.example.demo.base.exceptions.ValidationException;
import com.example.demo.base.service.response.ResponseService;
import com.example.demo.parking.exception.ParkingException;
import com.example.demo.parkingslot.exception.ParkingSlotException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.support.WebExchangeBindException;

@ControllerAdvice
@RequiredArgsConstructor
@Slf4j
public class ParkingControllerAdvice {

    private final ResponseService responseService;

    /**
     * Exception handler for @{@link WebExchangeBindException}.
     *
     * @param exception the @{@link WebExchangeBindException} to pass.
     * @return instance of @{@link ResponseEntity}.
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleWebExchangeBindException(MethodArgumentNotValidException exception) {
        return responseService.generateWebExBindExceptionErrorResponse(exception);
    }

    /**
     * Exception handlers for @{@link ValidationException}.
     *
     * @param ex the exception to pass.
     * @return instance of @{@link ResponseEntity}.
     */
    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<Object> handleValidationException(ValidationException ex) {
        return responseService.badRequest(ex.getErrorResponseItems());
    }

    /**
     * Exception handlers for @{@link ParkingSlotException}.
     *
     * @param exception the exception to pass.
     * @return instance of @{@link ResponseEntity}.
     */
    @ExceptionHandler(value = {ParkingSlotException.class})
    public ResponseEntity<ErrorResponse> handleResponseException(ParkingSlotException exception) {
        return responseService.generateErrorResponse(exception);
    }

    @ExceptionHandler(value = {ParkingException.class})
    public ResponseEntity<ErrorResponse> handleResponseException(ParkingException exception) {
        return responseService.generateErrorResponse(exception);
    }

    @ExceptionHandler(BaseException.class)
    public ResponseEntity<ErrorResponse> handleLicenseValidationException(BaseException validationException) {
        return responseService.badRequest(validationException.getErrors());
    }

}