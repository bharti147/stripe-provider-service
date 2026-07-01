package com.bharti.stripe_provider_service.service;

import com.bharti.stripe_provider_service.constant.ErrorCodeEnum;
import com.bharti.stripe_provider_service.exception.StripeProviderException;
import com.bharti.stripe_provider_service.pojo.CreatePaymentReq;
import com.bharti.stripe_provider_service.pojo.LineItem;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@Service
public class ValidationService {

    public void validate(CreatePaymentReq req){

        if(req == null){
            throw new StripeProviderException(
                    ErrorCodeEnum.CREATE_PAYMENT_REQ_NULL.getErrorCode(),
                    ErrorCodeEnum.CREATE_PAYMENT_REQ_NULL.getErrorMessage(),
                    HttpStatus.BAD_REQUEST
            );
        }

        //30002
        if (req.getSuccessUrl() == null || req.getSuccessUrl().trim().isEmpty()) {
            throw new StripeProviderException(
                   ErrorCodeEnum.SUCCESS_URL_MISSING.getErrorCode(),
                   ErrorCodeEnum.SUCCESS_URL_MISSING.getErrorMessage(),
                    HttpStatus.BAD_REQUEST
            );
        }

        //30003
        if (!isValidUrl(req.getSuccessUrl())) {
            throw new StripeProviderException(
                    ErrorCodeEnum.SUCCESS_URL_INVALID.getErrorCode(),
                    ErrorCodeEnum.SUCCESS_URL_INVALID.getErrorMessage(),
                    HttpStatus.BAD_REQUEST
            );
        }

        //30004
        if (req.getCancelUrl() == null || req.getCancelUrl().trim().isEmpty()) {
            throw new StripeProviderException(
                   ErrorCodeEnum.CANCEL_URL_MISSING.getErrorCode(),
                    ErrorCodeEnum.CANCEL_URL_MISSING.getErrorMessage(),
                    HttpStatus.BAD_REQUEST
            );
        }

        //30005
        if (!isValidUrl(req.getCancelUrl())) {
            throw new StripeProviderException(
                   ErrorCodeEnum.CANCEL_URL_INVALID.getErrorCode(),
                    ErrorCodeEnum.CANCEL_URL_INVALID.getErrorMessage(),
                    HttpStatus.BAD_REQUEST
            );
        }

        //30006
        List<LineItem> lineItems = req.getLineItems();

        if (lineItems == null || lineItems.isEmpty()) {
            throw new StripeProviderException(
                  ErrorCodeEnum.LINE_ITEMS_MISSING.getErrorCode(),
                 ErrorCodeEnum.LINE_ITEMS_MISSING.getErrorMessage(),
                    HttpStatus.BAD_REQUEST
            );
        }

        for(int i=0; i< lineItems.size(); i++){
            LineItem item = lineItems.get(i);

        //30007
            if(item == null){
                throw new StripeProviderException(
                       ErrorCodeEnum.LINE_ITEM_NULL.getErrorCode(),
                        ErrorCodeEnum.LINE_ITEM_NULL.getErrorMessage()  + " for line item " + (i+1),
                        HttpStatus.BAD_REQUEST
                );
            }

            //30008
            if(item.getCurrency() == null || item.getCurrency().trim().isEmpty()){
                throw new StripeProviderException(
                      ErrorCodeEnum.CURRENCY_MISSING.getErrorCode(),
                       ErrorCodeEnum.CURRENCY_MISSING.getErrorMessage() + " for line item " + (i+1),
                        HttpStatus.BAD_REQUEST
                );
            }

            //30009
            if (item.getProductName() == null || item.getProductName().trim().isEmpty()) {
                throw new StripeProviderException(
                       ErrorCodeEnum.PRODUCT_NAME_MISSING.getErrorCode(),
                       ErrorCodeEnum.PRODUCT_NAME_MISSING.getErrorMessage() + " for line item " + (i+1),
                        HttpStatus.BAD_REQUEST
                );
            }

            //30010
            if (item.getUnitAmount() <= 0) {
                throw new StripeProviderException(
                       ErrorCodeEnum.UNIT_AMOUNT_INVALID.getErrorCode(),
                       ErrorCodeEnum.UNIT_AMOUNT_INVALID.getErrorMessage()  + " for line item " + (i+1),
                        HttpStatus.BAD_REQUEST
                );
            }

            //30011
            if (item.getQuantity() <= 0) {
                throw new StripeProviderException(
                       ErrorCodeEnum.QUANTITY_INVALID.getErrorCode(),
                       ErrorCodeEnum.QUANTITY_INVALID.getErrorMessage()  + " for line item " + (i+1),
                        HttpStatus.BAD_REQUEST
                );
            }
        }




    }
    private boolean isValidUrl(String url) {
        try {
            URI uri = new URI(url);

            return uri.getScheme() != null
                    && (uri.getScheme().equalsIgnoreCase("http")
                    || uri.getScheme().equalsIgnoreCase("https"))
                    && uri.getHost() != null;

        } catch (URISyntaxException e) {
            return false;
        }
    }
}
