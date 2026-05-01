package com.bharti.stripe_provider_service.controller;
import com.bharti.stripe_provider_service.service.interfaces.PaymentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/v1/payments")
public class PaymentController {


    private final PaymentService paymentService;

    @PostMapping
    public String createPayment(){
     log.info(" Creating payment...");
     String response = paymentService.createPayment();
     log.info("Payment created: {}", response);
     return "Payment created successfully! Response: " + response;
    }
}
