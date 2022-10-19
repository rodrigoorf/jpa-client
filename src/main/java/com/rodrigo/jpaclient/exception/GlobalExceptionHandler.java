package com.rodrigo.jpaclient.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.ArrayList;
import java.util.List;

import static com.rodrigo.jpaclient.utils.AddressUtils.ZIP_CODE_TITLE;
import static com.rodrigo.jpaclient.utils.CustomerUtils.CUSTOMER_NOT_FOUND_TITLE;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(CustomerNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleCustomerNotFoundException(CustomerNotFoundException exception) {
        ErrorResponse errorResponse = new ErrorResponse(CUSTOMER_NOT_FOUND_TITLE, exception.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<ErrorResponse>> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        List<ErrorResponse> errorResponses = new ArrayList<>();
        exception.getBindingResult().getAllErrors().forEach(error ->
            errorResponses.add(new ErrorResponse(((FieldError) error).getField(), error.getDefaultMessage()))
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponses);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(InvalidZipCodeException.class)
    public ResponseEntity<ErrorResponse> handleInvalidZipCodeException(InvalidZipCodeException exception) {
        ErrorResponse errorResponse = new ErrorResponse(ZIP_CODE_TITLE, exception.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }
}
