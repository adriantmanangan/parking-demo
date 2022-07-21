package com.example.demo.base.service.response;

import java.util.List;

import com.example.demo.base.ErrorResponseItem;
import com.example.demo.base.ResponseErrorCode;
import com.example.demo.base.constants.ResponseMessageCode;
import com.example.demo.base.constants.SuccessMessageResponse;
import com.example.demo.parkingslot.advice.ErrorResponse;
import com.example.demo.parkingslot.exception.ParkingSlotException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.support.WebExchangeBindException;

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

    /**
     * build bad request errors.
     *
     * @param errors - Errors from exception
     * @return - response
     */
    public ResponseEntity<Object> badRequest(List<ErrorResponseItem> errors) {
        ResponseEntity<Object> response;
        if (errors.isEmpty()) {
            response = ResponseEntity.badRequest().build();
        } else {
            response = buildErrors(errors);
        }
        return response;
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

    private ResponseEntity<ErrorResponse> generateResponseEntity(Throwable ex, ResponseErrorCode errorCode, ErrorResponse errorResponse) {
        log.error("ErrorResponse Error: {}", errorResponse);
        log.error("", ex);
        return ResponseEntity
            .status(errorCode.getStatus())
            .body(errorResponse);
    }

    private ResponseEntity<Object> buildErrors(List<ErrorResponseItem> errors) {
        return ResponseEntity.badRequest()
            .body(errorResponseService.multipleErrors(HttpStatus.BAD_REQUEST, errors));
    }

    public SuccessMessageResponse createSuccessfulMessageResponse(ResponseMessageCode responseMessage, Object... arguments) {
        String msg = messageSource.getMessage(responseMessage.getMsgCode(), arguments, LocaleContextHolder.getLocale());
        return new SuccessMessageResponse(msg);
    }
}
