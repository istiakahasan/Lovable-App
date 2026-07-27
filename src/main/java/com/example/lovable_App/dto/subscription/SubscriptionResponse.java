package com.example.lovable_App.dto.subscription;

import java.time.Instant;

public record SubscriptionResponse(PlanResponse plan,
                                   String status,
                                   Instant periodEnd,
                                   Long tokensUsedThisCycle) {
}
