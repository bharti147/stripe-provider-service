package com.bharti.stripe_provider_service.exception;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@ToString(callSuper =  true)
public class StripeProviderException extends RuntimeException {
    private String errorCode;
    private String errorMessage;
    private HttpStatus httpStatus;
    public StripeProviderException(String errorCode, String errorMessage, HttpStatus httpStatus) {
        super(errorMessage);
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
        this.httpStatus =  httpStatus;
    }
}

