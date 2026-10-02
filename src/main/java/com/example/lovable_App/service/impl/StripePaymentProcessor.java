package com.example.lovable_App.service.impl;
import com.example.lovable_App.dto.subscription.CheckoutRequest;
import com.example.lovable_App.dto.subscription.CheckoutResponse;
import com.example.lovable_App.dto.subscription.PortalResponse;
import com.example.lovable_App.entity.Plan;
import com.example.lovable_App.entity.User;
import com.example.lovable_App.enums.SubscriptionStatus;
import com.example.lovable_App.error.ResourceNotFoundException;
import com.example.lovable_App.repository.PlanRepository;
import com.example.lovable_App.repository.UserRepository;
import com.example.lovable_App.security.AuthUtil;
import com.example.lovable_App.service.PaymentProcessor;
import com.example.lovable_App.service.SubscriptionService;
import com.stripe.exception.StripeException;
import com.stripe.model.*;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.time.Instant;
import java.util.Map;




@Slf4j
@Service
@RequiredArgsConstructor
public class StripePaymentProcessor implements PaymentProcessor {
    private final AuthUtil authUtil;
    private final PlanRepository planRepository;
    private final UserRepository userRepository;
    private final SubscriptionService subscriptionService;

    @Value("${client.url}")
    private String frontendUrl;

    @Override
    public CheckoutResponse createCheckoutSessionUrl(CheckoutRequest request) {
        Plan plan=planRepository.findById(request.planId())
                .orElseThrow(()-> new ResourceNotFoundException("Plan",request.planId()));

        Long userId=authUtil.getCurrentUserId();
        User user=userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User",userId));
//I copy and paste from the stripe-doc website nothing else
        SessionCreateParams.Builder params = SessionCreateParams.builder()
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
                .putMetadata("plan_id",plan.getId().toString());


        try {
            String stripeCustomerId= user.getStripeCustomerId();

            if(stripeCustomerId == null || stripeCustomerId.isEmpty()){
                params.setCustomerEmail(user.getUsername());
            }
            else {
            params.setCustomerEmail(stripeCustomerId);//stripe customer Id
            }

            Session session = Session.create(params.build());//making  api call to the stripe Backend
            return new CheckoutResponse(session.getUrl());
        } catch (StripeException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public PortalResponse openCustomerPortal(Long userId) {
        return null;
    }


    //**crucial event**
    @Override
    public void handleWebhookEvent(String type, StripeObject stripeObject, Map<String, String> metadata) {
    log.debug("Handling stripe event: {}",type);

    switch (type){
        case "checkout.session.completed"->handleCheckoutSessionCompleted((Session) stripeObject,metadata);//on-time,on checkout completed
        case "checkout.subscription.updated"-> handleCustomerSubscriptionUpdated((Subscription) stripeObject);//when user cancels, upgrades or  any updates
        case "checkout.subscription.deleted"-> handleCustomerSubscriptionDeleted((Subscription) stripeObject);//when subscription ends, revoke the subscription
        case "invoice.paid"->handleInvoicePaid((Invoice) stripeObject);//when invoice is paid
        case "invoice.payment_failed"->handleInvoicePaymentFailed((Invoice) stripeObject);//when invoice is not  paid,  mark as PAST_DUE
        default -> log.debug("Ignoring the event {}",type);
    }



    }




    //**************How to handle checkoutSession *****************************
    private void handleCheckoutSessionCompleted(Session session,Map<String, String> metadata){

        if(session ==null){
            log.error("Session object was null");
            return;
        }
    Long userId=Long.parseLong(metadata.get("user_id"));
    Long planId=Long.parseLong(metadata.get("plan_id"));

    String subscriptionId= session.getSubscription();
    String customerId=session.getCustomer();

    User user=getUser(userId);
    if(user.getStripeCustomerId()==null){
        user.setStripeCustomerId(customerId);
        userRepository.save(user);
    }
    subscriptionService.activateSubscription(userId,planId,subscriptionId,customerId);


    }
    private void handleCustomerSubscriptionUpdated(Subscription subscription){

        if(subscription == null){
            log.error("Subscription object was null");
            return;
        }
        SubscriptionStatus status= mapSrtipeStatusToEnum(subscription.getStatus());
        if(status == null){
            log.warn("Unknown status '{}'  for subscription {}",subscription.getStatus(),subscription.getId());

        }

        SubscriptionItem item=subscription.getItems().getData().get(0);
        Instant periodStart= toInstant(item.getCurrentPeriodStart());
        Instant periodEnd=toInstant(item.getCurrentPeriodEnd());

        Long planId=resolvePlanId(item.getPrice());


        subscriptionService.updateSubscription(subscription.getId(),status,periodStart,periodEnd,subscription.getCancelAtPeriodEnd(),planId);

    }




    private void handleCustomerSubscriptionDeleted(Subscription subscription){

        if(subscription == null){
            log.error("Subscription object was null inside handleCustomerSubscriptionDeleted");
            return;
        }
        subscriptionService.cancleSubscription(subscription.getId());

    }
    private void handleInvoicePaid(Invoice invoice){

        String subId=extractSubscriptionId(invoice);
        if(subId==null)return;
        try {
            Subscription subscription=Subscription.retrieve(subId);//sdk calling the stripe server
            var  item=subscription.getItems().getData().get(0);

            Instant periodStart= toInstant(item.getCurrentPeriodStart());
            Instant periodEnd=toInstant(item.getCurrentPeriodEnd());

            subscriptionService.renewSubscriptionPeriod(subId,periodStart,periodEnd);
        } catch (StripeException e) {
            throw new RuntimeException(e);
        }

    }
    private void handleInvoicePaymentFailed(Invoice invoice){

        String subId=extractSubscriptionId(invoice);
        if(subId==null)return;

        subscriptionService.markscriptionPaymentDue(subId);
    }

    //This method is called from the above method
    private User getUser(Long userId) {
        User user=userRepository.findById(userId).orElseThrow(
                ()-> new ResourceNotFoundException("User", userId));
        return user;
    }

    private SubscriptionStatus mapSrtipeStatusToEnum(String status){
        return switch (status){
            case"active"-> SubscriptionStatus.ACTIVE;
            case "trialing"->SubscriptionStatus.TRIALING;
            case "past_due","unpaid","paused","incomplete_expired"->SubscriptionStatus.PAST_DUE;
            case "canceled"->SubscriptionStatus.CANCELLED;
            case "incomplete"->SubscriptionStatus.INCOMPLETE;
            default -> {
                log.warn("Unmapped stripe status:{}",status);
                yield null;
            }
        };
    }

    //This method is  from above
    private Instant toInstant(Long epoch) {
        return epoch !=null ? Instant.ofEpochMilli(epoch) : null;
    }

    private Long resolvePlanId(Price price) {
        if(price ==null || price.getId() ==null)return null;
        return planRepository.findByStripePriceId(price.getId())
                .map(Plan::getId)
                        .orElse(null);
    }
    //
    //Doing invoice
    private String extractSubscriptionId( Invoice invoice){
        var parent= invoice.getParent();
        if(parent == null)return null;

        var  subDetails=parent.getSubscriptionDetails();
        if(subDetails ==null ) return null;

        return subDetails.getSubscription();
    }

}
