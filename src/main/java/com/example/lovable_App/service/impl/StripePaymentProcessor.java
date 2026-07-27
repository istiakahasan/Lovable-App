package com.example.lovable_App.service.impl;

import com.example.lovable_App.dto.subscription.CheckoutRequest;
import com.example.lovable_App.dto.subscription.CheckoutResponse;
import com.example.lovable_App.dto.subscription.PortalResponse;
import com.example.lovable_App.service.PaymentProcessor;

public class StripePaymentProcessor implements PaymentProcessor {
    @Override
    public CheckoutResponse createCheckoutSessionUrl(CheckoutRequest request) {
        return null;
    }

    @Override
    public PortalResponse openCustomerPortal(Long userId) {
        return null;
    }
}
