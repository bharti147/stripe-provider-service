package com.bharti.stripe_provider_service.pojo;

import lombok.Data;

@Data
public class ErrorResponse {
    private String errorCode;
    private String errorMessage;
}
