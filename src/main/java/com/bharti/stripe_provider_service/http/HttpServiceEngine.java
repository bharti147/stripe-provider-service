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


    public String makeHttpCall(){
        log.info("Making HTTP call tot external service");

        //headers
        HttpHeaders httpHeaders  = new HttpHeaders();
        httpHeaders.setBasicAuth("","");
        httpHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);


        //form data
        MultiValueMap<String, String> formUrlEncodedData = new LinkedMultiValueMap<>();
        formUrlEncodedData.add("mode","payment");
        formUrlEncodedData.add("success_url","https://example.com/success");
        formUrlEncodedData.add("line_items[0][quantity]","2");
        formUrlEncodedData.add("line_items[0][price_data][currency]","EUR");
        formUrlEncodedData.add("line_items[0][price_data][product_data][name]","Phone XXX");
        formUrlEncodedData.add("line_items[0][price_data][unit_amount]","100");


        //fluent api
            ResponseEntity<String> httpResponse = restClient.method(HttpMethod.POST)
                .uri("https://api.stripe.com/v1/checkout/sessions")
                .headers(t -> t.addAll(httpHeaders))
                .body(formUrlEncodedData)
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
