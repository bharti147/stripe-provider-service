package com.bharti.stripe_provider_service.service.helper;

import com.bharti.stripe_provider_service.constant.Constant;
import com.bharti.stripe_provider_service.http.HttpRequest;
import com.bharti.stripe_provider_service.pojo.CreatePaymentReq;
import com.bharti.stripe_provider_service.pojo.LineItem;
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

    public HttpRequest prepareStripeCreateSessionRequest(CreatePaymentReq createPaymentReq) {
        //headers
        HttpHeaders httpHeaders  = new HttpHeaders();
        httpHeaders.setBasicAuth(stripeApiKey,"");
        httpHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);


        //form data
        MultiValueMap<String, String> formUrlEncodedData = buildFormData(createPaymentReq);

        log.info("Prepared form URL encoded data for Stripe create-session API: {}", formUrlEncodedData);

        HttpRequest httpRequest = new HttpRequest();
        httpRequest.setHttpMethod(HttpMethod.POST);
        httpRequest.setUrl(stripeCreateSessionUrl);
        httpRequest.setHttpHeaders(httpHeaders);
        httpRequest.setRequestData(formUrlEncodedData);

        log.info("Prepared HttpRequest for Stripe create-session API: {}", httpRequest);

        return httpRequest;
    }

    public MultiValueMap<String, String> buildFormData(CreatePaymentReq createPaymentReq){
        MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
        formData.add(Constant.CREATE_SESSION_MODE, Constant.CREATE_SESSION_MODE_PAYMENT);
        formData.add(Constant.CREATE_SESSION_SUCCESS_URL, createPaymentReq.getSuccessUrl());
        formData.add(Constant.CREATE_SESSION_CANCEL_URL, createPaymentReq.getCancelUrl());

        for(int i=0; i<createPaymentReq.getLineItems().size(); i++){
            LineItem item = createPaymentReq.getLineItems().get(i);
            String baseKey =
                    Constant.LINE_ITEMS
                            + Constant.OPEN_BRACKET
                            + i
                            + Constant.CLOSE_BRACKET;

            formData.add(
                    baseKey
                            + Constant.OPEN_BRACKET + Constant.QUANTITY + Constant.CLOSE_BRACKET,
                    String.valueOf(item.getQuantity()));

            formData.add(
                    baseKey
                            + Constant.OPEN_BRACKET + Constant.PRICE_DATA + Constant.CLOSE_BRACKET
                            + Constant.OPEN_BRACKET + Constant.CURRENCY + Constant.CLOSE_BRACKET,
                    item.getCurrency());

            formData.add(
                    baseKey
                            + Constant.OPEN_BRACKET + Constant.PRICE_DATA + Constant.CLOSE_BRACKET
                            + Constant.OPEN_BRACKET + Constant.PRODUCT_DATA + Constant.CLOSE_BRACKET
                            + Constant.OPEN_BRACKET + Constant.NAME + Constant.CLOSE_BRACKET,
                    item.getProductName());

            formData.add(
                    baseKey
                            + Constant.OPEN_BRACKET + Constant.PRICE_DATA + Constant.CLOSE_BRACKET
                            + Constant.OPEN_BRACKET + Constant.UNIT_AMOUNT + Constant.CLOSE_BRACKET,
                    String.valueOf(item.getUnitAmount()));
        }
return  formData;

    }
}


//
//       formUrlEncodedData.add(CREATE_SESSION_MODE,"payment");
//        formUrlEncodedData.add(CREATE_SESSION_SUCCESS_URL,"https://example.com/success");
//        formUrlEncodedData.add("line_items[0][quantity]","2");
//        formUrlEncodedData.add("line_items[0][price_data][currency]","EUR");
//        formUrlEncodedData.add("line_items[0][price_data][product_data][name]","Phone Samau");
//        formUrlEncodedData.add("line_items[0][price_data][unit_amount]","100");