package com.bharti.stripe_provider_service.exception;

import com.bharti.stripe_provider_service.pojo.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(StripeProviderException.class)
    public ResponseEntity<ErrorResponse> handleStripeProviderExcpetion(StripeProviderException ex){
        log.error("StripeProviderException caught: {}", ex.toString());

        HttpStatus status = ex.getHttpStatus();
        ErrorResponse errorResponse =  new ErrorResponse();
        errorResponse.setErrorCode(ex.getErrorCode());
        errorResponse.setErrorMessage(ex.getErrorMessage());

        log.error("Returning error response: status={}, body={}", status, errorResponse);
        return new ResponseEntity<>(errorResponse, status);
    }

}
