package com.bharti.stripe_provider_service.http;

import lombok.Data;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;

@Data
public class HttpRequest {
    private HttpMethod httpMethod;
    private String url;
    private HttpHeaders httpHeaders;
    private Object requestData;
}
