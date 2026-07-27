package com.example.lovable_App.entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.springframework.boot.webmvc.autoconfigure.WebMvcProperties;

import java.time.Instant;
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProjectFile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    Project project;

    String path;
    String miniObjectKey;

    Instant createdAt;

    Instant updatedAt;
    User createdBy;
    User updatedBy;
}
