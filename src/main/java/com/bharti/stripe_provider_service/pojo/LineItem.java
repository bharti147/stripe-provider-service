package com.bharti.stripe_provider_service.pojo;

import lombok.Data;

//represent 1 product in cart and @data for getters setters of these fields
@Data
public class LineItem {
    private String currency;
    private String productName;
    private int unitAmount;
    private int quantity;
}
