package com.example.demo.base.service.response;

import java.util.List;
import java.util.stream.Collectors;

import com.example.demo.base.ApiErrorCode;
import com.example.demo.base.ErrorResponseItem;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

@RequiredArgsConstructor
public class ErrorResponseItemService {
    private final MessageSource messageSource;

    /**
     * Converts List of object errors to list of error response items.
     *
     * @param apiErrorCode api error code
     * @param objectErrors list of object errors
     * @return list of ErrorResponseItem objects
     */
    public List<ErrorResponseItem> toErrorResponseItems(ApiErrorCode apiErrorCode, List<ObjectError> objectErrors) {
        return objectErrors
            .stream()
            .map(error -> toErrorResponseItem(apiErrorCode, error))
            .collect(Collectors.toList());
    }

    /**
     * Converts object error to error response item.
     *
     * @param apiErrorCode api error code
     * @param objectError  object error
     * @return ErrorResponseItem object
     */
    public ErrorResponseItem toErrorResponseItem(ApiErrorCode apiErrorCode, ObjectError objectError) {
        String errorMessage = messageSource.getMessage(objectError, LocaleContextHolder.getLocale());

        if (objectError instanceof FieldError) {
            return new ErrorResponseItem(apiErrorCode, errorMessage, ((FieldError) objectError).getField());
        } else {
            return new ErrorResponseItem(apiErrorCode, errorMessage);
        }
    }

}
