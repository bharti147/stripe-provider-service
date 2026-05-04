package com.bharti.stripe_provider_service.service.helper;

import com.bharti.stripe_provider_service.http.HttpRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import static com.bharti.stripe_provider_service.constant.Constant.CREATE_SESSION_MODE;
import static com.bharti.stripe_provider_service.constant.Constant.CREATE_SESSION_SUCCESS_URL;

@Service
@Slf4j
public class CreatePaymentHelper {


    @Value("${stripe.api.key}")
       private String stripeApiKey;

    @Value("${stripe.create.session.url}")
        private String stripeCreateSessionUrl;

    public HttpRequest prepareStripeCreateSessionRequest() {
        //headers
        HttpHeaders httpHeaders  = new HttpHeaders();
        httpHeaders.setBasicAuth(stripeApiKey,"");
        httpHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);


        //form data
        MultiValueMap<String, String> formUrlEncodedData = new LinkedMultiValueMap<>();
        formUrlEncodedData.add(CREATE_SESSION_MODE,"payment");
        formUrlEncodedData.add(CREATE_SESSION_SUCCESS_URL,"https://example.com/success");
        formUrlEncodedData.add("line_items[0][quantity]","2");
        formUrlEncodedData.add("line_items[0][price_data][currency]","EUR");
        formUrlEncodedData.add("line_items[0][price_data][product_data][name]","Phone XXX");
        formUrlEncodedData.add("line_items[0][price_data][unit_amount]","100");

        HttpRequest httpRequest = new HttpRequest();
        httpRequest.setHttpMethod(HttpMethod.POST);
        httpRequest.setUrl(stripeCreateSessionUrl);
        httpRequest.setHttpHeaders(httpHeaders);
        httpRequest.setRequestData(formUrlEncodedData);
        return httpRequest;
    }
}
