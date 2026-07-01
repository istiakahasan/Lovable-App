package com.example.lovable_App.entity;

import jakarta.persistence.Embeddable;
import lombok.*;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProjectMemberId {
    Long projectId;
    Long userId;
}
