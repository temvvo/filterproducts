package com.ecommerce.filterproducts.infrastructure.adapter.api.exceptions.handler;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.io.IOException;

@Slf4j
@ControllerAdvice
public class FPErrorHandler {

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity defaultErrorHandler(HttpServletRequest req, Exception e) {

        log.error("RestExceptionHandler Exception handler. Server error", e);

        return new ResponseEntity<>("Internal Error: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = IOException.class)
    public ResponseEntity IOErrorHandler(HttpServletRequest req, Exception e) {

        log.error("RestExceptionHandler Exception handler. File IO Error", e);

        return new ResponseEntity<>("File IO Error: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
