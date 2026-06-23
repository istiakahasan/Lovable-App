package com.example.lovable_App.dto.subscription;

public record UsageTodayResponse(Integer tokenUsed,
                                  Integer tokenLimit,
                                  Integer previewRunning,
                                  Integer previewLimit) {
}
