package com.bharti.stripe_provider_service.pojo;

import lombok.Data;

@Data
public class PaymentResponse {
 private String stripeSessionId;
 private String hostedPageUrl;
// private String status;
}
