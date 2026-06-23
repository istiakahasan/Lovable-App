package com.example.lovable_App.service;

import com.example.lovable_App.dto.subscription.PlanLimitsResponse;
import com.example.lovable_App.dto.subscription.UsageTodayResponse;

public interface UsageService {
    

    UsageTodayResponse getTodayUsageOfUser(UsageService usageService);

    PlanLimitsResponse getCurrentSubscriptionLimitsOfUser(Long userId);
}
