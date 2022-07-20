package com.example.demo.parking.service.response;

import com.example.demo.parking.constants.ResponseMessageCode;
import com.example.demo.parking.constants.SuccessMessageResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class ResponseService {

    private final MessageSource messageSource;

    public <T> ResponseEntity<T> ok(T body) {
        return ResponseEntity.ok(body);
    }

    public <T> ResponseEntity<T> badRequest() {
        return ResponseEntity.badRequest().build();
    }

    public SuccessMessageResponse createSuccessfulMessageResponse(ResponseMessageCode responseMessage, Object... arguments) {
        String msg = messageSource.getMessage(responseMessage.getMsgCode(), arguments, LocaleContextHolder.getLocale());
        return new SuccessMessageResponse(msg);
    }
}
