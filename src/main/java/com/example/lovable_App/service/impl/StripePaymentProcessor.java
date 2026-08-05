package com.example.lovable_App.service.impl;

import com.example.lovable_App.dto.subscription.CheckoutRequest;
import com.example.lovable_App.dto.subscription.CheckoutResponse;
import com.example.lovable_App.dto.subscription.PortalResponse;
import com.example.lovable_App.entity.Plan;
import com.example.lovable_App.error.ResourceNotFoundException;
import com.example.lovable_App.repository.PlanRepository;
import com.example.lovable_App.security.AuthUtil;
import com.example.lovable_App.service.PaymentProcessor;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StripePaymentProcessor implements PaymentProcessor {
    private final AuthUtil authUtil;
    private final PlanRepository planRepository;

    @Value("${client.url}")
    private String frontendUrl;

    @Override
    public CheckoutResponse createCheckoutSessionUrl(CheckoutRequest request) {
        Plan plan=planRepository.findById(request.planId())
                .orElseThrow(()-> new ResourceNotFoundException("Plan",request.planId()));
        Long userId=authUtil.getCurrentUserId();
//I copy and paste from the stripe-doc website nothing else
        SessionCreateParams params = SessionCreateParams.builder()
                .addLineItem(
                        SessionCreateParams.LineItem.builder().setPrice(plan.getStripePriceId()).setQuantity(1L).build())
                .setMode(SessionCreateParams.Mode.SUBSCRIPTION)
                .setSubscriptionData(
                        new SessionCreateParams.SubscriptionData.Builder()
                                .setBillingMode(SessionCreateParams.SubscriptionData.BillingMode.builder()
                                .setType(SessionCreateParams.SubscriptionData.BillingMode.Type.FLEXIBLE).build()).build()
                )
                .setSuccessUrl(frontendUrl + "/success.html?session_id={CHECKOUT_SESSION_ID}")
                .setCancelUrl(frontendUrl + "/cancel.html")
                .putMetadata("user_id",userId.toString())
                .putMetadata("plan_id",plan.getId().toString())
                .build();
        try {
            Session session = Session.create(params);//making  api call to the stripe Backend
            return new CheckoutResponse(session.getUrl());
        } catch (StripeException e) {
            throw new RuntimeException(e);
        }


    }

    @Override
    public PortalResponse openCustomerPortal(Long userId) {
        return null;
    }
}
