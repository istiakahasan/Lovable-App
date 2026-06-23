package com.example.lovable_App.dto.subscription;

public record PlanResponse(Long id,
                           String name,
                           Integer maxTokensPerDay,
                           Integer  maxProjects,
                           Boolean unLimitedAi,
                           String price) {


}
