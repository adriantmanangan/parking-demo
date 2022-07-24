package com.example.demo.base.service.response;

import com.example.demo.base.ErrorResponseItem;
import com.example.demo.base.ResponseErrorCode;
import com.example.demo.base.constants.ResponseMessageCode;
import com.example.demo.base.constants.SuccessMessageResponse;
import com.example.demo.parking.exception.ParkingException;
import com.example.demo.parkingslot.advice.ErrorResponse;
import com.example.demo.parkingslot.exception.ParkingSlotException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.web.bind.MethodArgumentNotValidException;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class ResponseService {

    private final MessageSource messageSource;
    private final ErrorResponseService errorResponseService;
    private final ErrorResponseItemService errorResponseItemService;

    public <T> ResponseEntity<T> ok(T body) {
        return ResponseEntity.ok(body);
    }

    public <T> ResponseEntity<T> badRequest() {
        return ResponseEntity.badRequest().build();
    }

    public ResponseEntity<ErrorResponse> badRequest(Errors errors) {
        return ResponseEntity.badRequest()
            .body(errorResponseService.multipleErrors(HttpStatus.BAD_REQUEST, ResponseErrorCode.VALIDATION_ERROR, errors));
    }

    /**
     * Generate @{@link ResponseEntity} for the WebExBindException.
     *
     * @param exception the dto to pass.
     * @return instance of @{@link ResponseEntity}.
     */
    public ResponseEntity<ErrorResponse> generateWebExBindExceptionErrorResponse(MethodArgumentNotValidException exception) {
        ResponseErrorCode responseErrorCode = ResponseErrorCode.VALIDATION_ERROR;
        List<ErrorResponseItem> items = errorResponseItemService.toErrorResponseItems(responseErrorCode, exception.getAllErrors());
        ErrorResponse errorResponse = errorResponseService.multipleErrors(responseErrorCode.getStatus(), items);
        return generateResponseEntity(exception, responseErrorCode, errorResponse);
    }

    /**
     * Generate @{@link ResponseEntity} for the ApplicationError.
     *
     * @param exception the @{@link ParkingSlotException} to pass.
     * @return instance of @{@link ResponseEntity}.
     */
    public ResponseEntity<ErrorResponse> generateErrorResponse(ParkingSlotException exception) {
        String message = messageSource.getMessage(exception.getResponseErrorCode().getMsgCode(), exception.getErrorArguments(),
            LocaleContextHolder.getLocale());
        ResponseErrorCode responseErrorCode = exception.getResponseErrorCode();
        ErrorResponse errorResponse = errorResponseService.singleError(responseErrorCode.getStatus(), responseErrorCode, message);
        return generateResponseEntity(exception, responseErrorCode, errorResponse);
    }

    /**
     * Generate @{@link ResponseEntity} for the ApplicationError.
     *
     * @param exception the @{@link ParkingSlotException} to pass.
     * @return instance of @{@link ResponseEntity}.
     */
    public ResponseEntity<ErrorResponse> generateErrorResponse(ParkingException exception) {
        String message = messageSource.getMessage(exception.getResponseErrorCode().getMsgCode(), exception.getErrorArguments(),
            LocaleContextHolder.getLocale());
        ResponseErrorCode responseErrorCode = exception.getResponseErrorCode();
        ErrorResponse errorResponse = errorResponseService.singleError(responseErrorCode.getStatus(), responseErrorCode, message);
        return generateResponseEntity(exception, responseErrorCode, errorResponse);
    }

    /**
     * Generate @{@link ResponseEntity} for the Application Error
     * @param ex the @{@link Throwable} to pass
     * @param errorCode instance of @{@link ResponseErrorCode}
     * @param errorResponse instance of @{@link ErrorResponse}
     * @return ResponseEntity object
     */
    private ResponseEntity<ErrorResponse> generateResponseEntity(Throwable ex, ResponseErrorCode errorCode, ErrorResponse errorResponse) {
        log.error("ErrorResponse Error: {}", errorResponse);
        log.error("", ex);
        return ResponseEntity
            .status(errorCode.getStatus())
            .body(errorResponse);
    }

    public SuccessMessageResponse createSuccessfulMessageResponse(ResponseMessageCode responseMessage, Object... arguments) {
        String msg = messageSource.getMessage(responseMessage.getMsgCode(), arguments, LocaleContextHolder.getLocale());
        return new SuccessMessageResponse(msg);
    }

    public ResponseEntity<ErrorResponse> badRequest(ConstraintViolationException exception) {
        return ResponseEntity.badRequest().body(multipleErrors(ResponseErrorCode.VALIDATION_ERROR, exception.getConstraintViolations()));
    }
    private ErrorResponse multipleErrors(ResponseErrorCode code, Set<ConstraintViolation<?>> constraintViolations) {
        List<ErrorResponseItem> errorResponseItems = constraintViolations
                .stream()
                .map(error -> {
                    String errorMessage = messageSource.getMessage(error.getMessage(), null, error.getMessage(),
                            LocaleContextHolder.getLocale());
                    return new ErrorResponseItem(code, errorMessage);
                })
                .collect(Collectors.toList());

        return new ErrorResponse(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), errorResponseItems);

    }
}
