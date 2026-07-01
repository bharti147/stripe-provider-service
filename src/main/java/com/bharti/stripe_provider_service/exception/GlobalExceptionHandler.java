package com.bharti.stripe_provider_service.exception;

import com.bharti.stripe_provider_service.constant.ErrorCodeEnum;
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

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGenericExcpetion(Exception ex){
        log.error("Generic exception caught: {}", ex.toString());

        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        ErrorResponse errorResponse =  new ErrorResponse();
        errorResponse.setErrorCode(ErrorCodeEnum.GENERIC_ERROR.getErrorCode());
        errorResponse.setErrorMessage(ErrorCodeEnum.GENERIC_ERROR.getErrorMessage());

        log.error("Returning error response: status={}, body={}", status, errorResponse);
        return new ResponseEntity<>(errorResponse, status);
    }

}
