package com.bharti.stripe_provider_service.constant;

public enum ErrorCodeEnum {

    GENERIC_ERROR(
            "30000",
            "Unexpected error occurred. Please try again later"
    ),

    CREATE_PAYMENT_REQ_NULL(
            "30001",
            "CreatePaymentReq is null"
    ),
    SUCCESS_URL_MISSING(
            "30002",
            "Success URL is required"
    ),

    SUCCESS_URL_INVALID(
            "30003",
            "Success URL is invalid"
    ),

    CANCEL_URL_MISSING(
            "30004",
            "Cancel URL is required"
    ),

    CANCEL_URL_INVALID(
            "30005",
            "Cancel URL is invalid"
    ),

    LINE_ITEMS_MISSING(
            "30006",
            "At least one line item is required"
    ),

    LINE_ITEM_NULL(
            "30007",
            "Line item is null"
    ),

    CURRENCY_MISSING(
            "30008",
            "Currency is required"
    ),

    PRODUCT_NAME_MISSING(
            "30009",
            "Product name is required"
    ),

    UNIT_AMOUNT_INVALID(
            "30010",
            "Unit amount must be greater than 0"
    ),

    QUANTITY_INVALID(
            "30011",
            "Quantity must be greater than 0"
    );


    private final String errorCode;
    private final String errorMessage;

    ErrorCodeEnum(String errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }
    public String getErrorCode() {
        return errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
