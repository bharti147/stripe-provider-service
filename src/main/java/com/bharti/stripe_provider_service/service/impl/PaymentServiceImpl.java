package com.bharti.stripe_provider_service.service.impl;

import com.bharti.stripe_provider_service.exception.StripeProviderException;
import com.bharti.stripe_provider_service.http.HttpRequest;
import com.bharti.stripe_provider_service.http.HttpServiceEngine;
import com.bharti.stripe_provider_service.pojo.CreatePaymentReq;
import com.bharti.stripe_provider_service.pojo.PaymentResponse;
import com.bharti.stripe_provider_service.service.helper.CreatePaymentHelper;
import com.bharti.stripe_provider_service.service.interfaces.PaymentService;
import com.bharti.stripe_provider_service.stripe.CheckoutSessionResponse;
import com.bharti.stripe_provider_service.util.JsonUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;



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
    private final JsonUtil jsonUtil;


    @Override
    public PaymentResponse createPayment(CreatePaymentReq createPaymentReq) {
        log.info("Processing payment creation logic");

        if(createPaymentReq.getSuccessUrl()==null || createPaymentReq.getSuccessUrl().isEmpty()){
            log.error("Success URL is missing createPaymentReq");
            throw new StripeProviderException(
                    "30001",
                    "Success url is required to create a stripe checkout session",
                    HttpStatus.BAD_REQUEST
            );
        }

        HttpRequest httpRequest = createPaymentHelper.prepareStripeCreateSessionRequest(createPaymentReq);


        ResponseEntity<String> httpResponse = httpServiceEngine.makeHttpCall(httpRequest);

        log.info("Received response from HttpServiceEngine: {}", httpResponse);

        //Convert json response to CheckoutSessionResponse
        CheckoutSessionResponse checkoutSessionResponse = jsonUtil.convertJsonToObject(httpResponse.getBody(), CheckoutSessionResponse.class);
        log.info("Converted CheckoutSessionResponse: {}", checkoutSessionResponse);

        PaymentResponse paymentResponse = mapCheckoutSessionToPaymentResponse(checkoutSessionResponse);
        log.info("Mapped PaymentResponse: {}", paymentResponse);
        return paymentResponse;
    }


    /* a map method to take checkoutsessionresponse and convert it into
    PaymentResponse whcih is our internal response object
     */
    public PaymentResponse mapCheckoutSessionToPaymentResponse(CheckoutSessionResponse checkoutSessionResponse) {
        if (checkoutSessionResponse == null) {
            log.warn("mapCheckoutSessionToPaymentResponse called with null");
            return null;
        }

        PaymentResponse paymentResponse = new PaymentResponse();
        paymentResponse.setStripeSessionId(checkoutSessionResponse.getId());
        paymentResponse.setHostedPageUrl(checkoutSessionResponse.getUrl());
//         paymentResponse.setStatus(checkoutSessionResponse.getStatus());

        log.info("Mapped CheckoutSessionResponse to PaymentResponse: {}", paymentResponse);
        return paymentResponse;
    }


}
