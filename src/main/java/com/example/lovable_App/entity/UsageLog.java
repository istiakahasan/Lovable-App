package com.example.lovable_App.entity;



import java.time.Instant;


public class UsageLog {
    Long id;
    User user;
    Project project;

    String action;
    Integer tokenUsed;
    Integer durationMs;

    String metaData;//JSON of a (model used,prompt used)

    Instant createdAt;

}
