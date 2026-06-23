package com.example.lovable_App.dto.subscription;

public record PlanLimitsResponse(String planName,
                                 Integer maxTokenPerDay,
                                 Integer maxProjects,
                                 boolean unLimitedAi) {
}
