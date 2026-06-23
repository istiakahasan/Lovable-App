package com.example.lovable_App.service;

import com.example.lovable_App.dto.subscription.PlanResponse;

import java.util.List;

public interface PlanService {
    List<PlanResponse> getAllActivePlans();
}
