package com.bharti.stripe_provider_service.service.impl;

import com.bharti.stripe_provider_service.http.HttpRequest;
import com.bharti.stripe_provider_service.http.HttpServiceEngine;
import com.bharti.stripe_provider_service.service.helper.CreatePaymentHelper;
import com.bharti.stripe_provider_service.service.interfaces.PaymentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;


/*
 * Business logic : Make Rest API call to Stripe create-session API
 * Then, TO-DO will be ->
 * Prepare the request to call stripe bcz here our app is a client for stripe and server for the customer
 * url, post, form-encoded request body, basic auth header
 * What spring boot library is available to make API call to stripe
 * How to use RestClient to make API call to stripe
 * Response handling. Both success & failure
 * */

@Service
@Slf4j
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final HttpServiceEngine httpServiceEngine;
    private final CreatePaymentHelper createPaymentHelper;

    @Override
    public String createPayment() {
        log.info("Processing payment creation logic");
        HttpRequest httpRequest = createPaymentHelper.prepareStripeCreateSessionRequest();


        String httpResponse =  httpServiceEngine.makeHttpCall(httpRequest);

        log.info("Received response from HttpServiceEngine: {}", httpResponse);

        return httpResponse;
    }


}
