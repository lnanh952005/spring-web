package com.javaweb.buildingproject.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity(name = "user")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username")
    private String userName;

    @Column(name = "password")
    private String passWord;

    @Column(name = "phone")
    private Long phone;

    @Column(name = "email")
    private String email;

    @Column(name = "status")
    private Long status;

    @OneToMany(mappedBy = "user",fetch = FetchType.LAZY)
    private List<UserRoleEntity> userRole;
}
