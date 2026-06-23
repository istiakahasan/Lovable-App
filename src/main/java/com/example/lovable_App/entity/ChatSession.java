package com.example.lovable_App.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.springframework.data.projection.EntityProjectionIntrospector;

import java.time.Instant;
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ChatSession {

    Project project;
    User user;
    String title;

    Instant createdAt;

    Instant updatedAt;

    Instant deletedAt;
}
