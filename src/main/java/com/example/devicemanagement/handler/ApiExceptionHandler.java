package com.example.devicemanagement.handler;

import java.util.Arrays;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.example.devicemanagement.common.ApiException;
import com.example.devicemanagement.common.BaseResponse;

@ControllerAdvice
public class ApiExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidBody(MethodArgumentNotValidException ex) {
        return new ResponseEntity<>(
                BaseResponse.error(
                        ex.getAllErrors().stream().map(ObjectError::getDefaultMessage).collect(Collectors.toList())),
                HttpStatus.BAD_REQUEST);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ApiException.class)
    public ResponseEntity<?> handleBusinessException(ApiException ex) {
        return new ResponseEntity<>(BaseResponse.error(Arrays.asList(ex.getMessage(), ex.getDetails())),
                HttpStatus.BAD_REQUEST);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleBusinessException(Exception ex) {
        return new ResponseEntity<>(BaseResponse.error(Arrays.asList(ex.getMessage())), HttpStatus.BAD_REQUEST);
    }
}
