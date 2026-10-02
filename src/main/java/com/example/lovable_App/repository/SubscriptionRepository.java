package com.example.lovable_App.repository;

import com.example.lovable_App.dto.subscription.SubscriptionResponse;
import com.example.lovable_App.entity.Subscription;
import com.example.lovable_App.enums.SubscriptionStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface SubscriptionRepository extends JpaRepository<Subscription,Long> {
    SubscriptionResponse findByUserIdAndStatusIn(Long userId, Set<SubscriptionStatus> active);
}
