package com.bharti.stripe_provider_service.service.interfaces;

import com.bharti.stripe_provider_service.pojo.CreatePaymentReq;
import com.bharti.stripe_provider_service.pojo.PaymentResponse;

public interface PaymentService {
    public PaymentResponse createPayment(CreatePaymentReq createPaymentReq);
}
