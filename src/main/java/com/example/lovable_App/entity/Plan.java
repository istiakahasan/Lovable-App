package com.example.lovable_App.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Plan {
    Long id;

    String name;

    String striptPriceId;
    Integer maxProjects;

    Integer maxTokensPerDay;
    Integer maxPreviews;
    Boolean unLimitedAi;//unlimited access ot llm

    Boolean active;
}
