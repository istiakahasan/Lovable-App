package com.example.lovable_App.repository;

import com.example.lovable_App.entity.ProjectMember;
import com.example.lovable_App.entity.ProjectMemberId;
import com.example.lovable_App.enums.ProjectRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@Repository
public interface ProjectMemberRepository extends JpaRepository<ProjectMember, ProjectMemberId> {

    List<ProjectMember> findByProjectId(Long projectId);

    @Query("""
            SELECT pn.projectRole FROM ProjectMember pn
            WHERE pn.id.projectId = :projectId AND pn.id.userId= :userId
            """)
    Optional<ProjectRole> findRoleByProjectIdAndUserId(@Param("projectId") Long projectId,
                                                       @Param("userId") Long userId);
}
