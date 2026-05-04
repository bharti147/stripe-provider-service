package com.bharti.stripe_provider_service.http;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClient;

import java.util.function.Consumer;

@Component
@Slf4j
@RequiredArgsConstructor
public class HttpServiceEngine {

    private final RestClient restClient;


    public String makeHttpCall(HttpRequest httpRequest){
        log.info("Making HTTP call tot external service");




        //fluent api
            ResponseEntity<String> httpResponse = restClient.method(httpRequest.getHttpMethod())
                .uri(httpRequest.getUrl())
                .headers(t -> t.addAll(httpRequest.getHttpHeaders()))
                .body(httpRequest.getRequestData())
                .retrieve()
                .toEntity(String.class);

            log.info("HTTP call completed. Status code: {}, Response body: {}", httpResponse.getStatusCode(), httpResponse.getBody());


        return "\n" + httpResponse.getBody();

    }

@PostConstruct
    public void init(){
        log.info("Initializing  HttpServiceEngine...restClient: {}", restClient);
    }






}
