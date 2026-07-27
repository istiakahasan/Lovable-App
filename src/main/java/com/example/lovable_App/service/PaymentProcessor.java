package com.example.lovable_App.service;

import com.example.lovable_App.dto.subscription.CheckoutRequest;
import com.example.lovable_App.dto.subscription.CheckoutResponse;
import com.example.lovable_App.dto.subscription.PortalResponse;

public interface PaymentProcessor {
    CheckoutResponse createCheckoutSessionUrl(CheckoutRequest request);

    PortalResponse openCustomerPortal(Long userId);
}
