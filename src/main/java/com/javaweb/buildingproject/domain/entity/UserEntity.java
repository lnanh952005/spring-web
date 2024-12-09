package com.javaweb.buildingproject.domain.entity;

import com.javaweb.buildingproject.utils.SecurityUtils;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
@Entity
@Getter
@Setter
@Table(name = "users")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username")
    private String username;

    @Column(name = "password",columnDefinition = "MEDIUMTEXT")
    private String password;

    @Column(name = "fullname")
    private String fullname;

    @Column(name = "phone")
    private Long phone;

    @Column(name = "email")
    private String email;

    @Column(name = "age")
    private Long age;

    @Column(name = "gender")
    private String gender;

    @Column(name = "address")
    private String address;

    @Column(name = "refreshtoken",columnDefinition = "MEDIUMTEXT")
    private String refreshToken;

    @Column(name = "createdat")
    private Instant createdAt;

    @Column(name = "updatedat")
    private Instant updatedAt;

    @Column(name = "createdby")
    private String createdBy;

    @Column(name = "updatedby")
    private String updatedBy;

    @ManyToOne
    @JoinColumn(name = "company_id")
    private CompanyEntity company;

    @PrePersist
    void preCreatedAt() {
        this.createdAt = Instant.now();
        this.createdBy = SecurityUtils.getUserDetails().getName();
    }

    @PreUpdate
    void preUpdatedAt() {
        this.updatedAt = Instant.now();
        this.updatedBy = SecurityUtils.getUserDetails().getName();
    }
}