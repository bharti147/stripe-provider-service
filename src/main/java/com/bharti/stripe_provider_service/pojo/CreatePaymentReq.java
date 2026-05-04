package com.bharti.stripe_provider_service.pojo;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class CreatePaymentReq {

    private String successUrl;
    private String cancelUrl;

//As, there can be any products in cart
    private List<LineItem> lineItems = new ArrayList<>();



}
