package com.javaweb.buildingproject.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.javaweb.buildingproject.enums.LevelEnum;
import com.javaweb.buildingproject.utils.SecurityUtils;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.util.List;

@Entity
@Table(name = "jobs")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class JobEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "location")
    private String location;

    @Column(name = "salary")
    private Double salary;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "level")
    @Enumerated(EnumType.STRING)
    private LevelEnum level;

    @Column(name = "description",columnDefinition = "MEDIUMTEXT")
    private String description;

    @Column(name = "start_date")
    private Instant startDate;

    @Column(name = "end_date")
    private Instant endDate;

    @Column(name = "active")
    private Boolean Active;

    @Column(name = "created_at")
    private Instant createdAt;

    @Column (name = "updated_at")
    private Instant updatedAt;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "updated_by")
    private String updatedBy;

    @ManyToOne
    @JoinColumn(name = "company_id")
    private CompanyEntity company;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "job_skill", joinColumns = @JoinColumn(name = "job_id"),inverseJoinColumns = @JoinColumn(name = "skill_id"))
    @JsonIgnore
    private List<SkillEntity> skill;

    @PrePersist
    private void handleBeforeCreate() {
        this.setActive(true);
        this.createdAt = Instant.now();
        this.createdBy = SecurityUtils.getUserDetails().getName();
    }

    @PreUpdate
    private void handleBeforeUpdate() {
        this.updatedAt = Instant.now();
        this.updatedBy = SecurityUtils.getUserDetails().getName();
    }

}
