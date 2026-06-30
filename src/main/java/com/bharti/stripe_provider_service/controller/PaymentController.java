package com.bharti.stripe_provider_service.controller;
import com.bharti.stripe_provider_service.pojo.CreatePaymentReq;
import com.bharti.stripe_provider_service.pojo.PaymentResponse;
import com.bharti.stripe_provider_service.service.interfaces.PaymentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/v1/payments")
public class PaymentController {


    private final PaymentService paymentService;

    @PostMapping
    public PaymentResponse createPayment(@RequestBody CreatePaymentReq createPaymentReq){
     log.info(" Creating payment...createPaymentReq: {}", createPaymentReq );

         PaymentResponse paymentResponse = paymentService.createPayment(createPaymentReq);
     log.info("Payment created: {}", paymentResponse);
     return paymentResponse;
    }
}
