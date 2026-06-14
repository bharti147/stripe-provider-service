package com.bharti.stripe_provider_service.stripe;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CheckoutSessionResponse {
    private String id;
    private String status;
    private String url;

    @JsonProperty("payment_status")
    private String paymentStatus;


}
