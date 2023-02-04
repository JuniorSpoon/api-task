package com.supercassa.apitask.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(value = {ApiRequestException.class})
    public ResponseEntity<Object> handleRequestException(ApiRequestException e) {

        HttpStatus aTeapot = HttpStatus.I_AM_A_TEAPOT;
        ApiException apiException = new ApiException(e.getMessage(), aTeapot);

        return new ResponseEntity<>(apiException, aTeapot);
    }
}
