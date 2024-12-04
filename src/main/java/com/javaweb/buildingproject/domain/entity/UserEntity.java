package com.javaweb.buildingproject.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.List;
@Entity
@Getter
@Setter
@Table(name = "user")
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

    @PrePersist
    void preCreatedAt() {
        this.createdAt = Instant.now();
        this.createdBy = "le nhat anh";
    }

    @PreUpdate
    void preUpdatedAt() {
        this.updatedAt = Instant.now();
        this.updatedBy = "le nhat anh";
    }
}