package com.example.lovable_App.service.impl;

import com.example.lovable_App.dto.auth.UserProfileResponse;
import com.example.lovable_App.dto.subscription.PlanLimitsResponse;
import com.example.lovable_App.dto.subscription.UsageTodayResponse;
import com.example.lovable_App.service.UsageService;
import com.example.lovable_App.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UsageServiceImpl implements UsageService {



    @Override
    public UsageTodayResponse getTodayUsageOfUser(UsageService usageService) {
        return null;
    }

    @Override
    public PlanLimitsResponse getCurrentSubscriptionLimitsOfUser(Long userId) {
        return null;
    }
}
