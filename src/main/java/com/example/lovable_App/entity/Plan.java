package com.example.lovable_App.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class Plan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String name;

    @Column(unique = true)
    String stripePriceId;
    Integer maxProjects;

    Integer maxTokensPerDay;
    Integer maxPreviews;//max number of previews allowed per plan
    Boolean unLimitedAi;//unlimited access ot llm

    Boolean active;
}
