package com.javaweb.buildingproject.domain.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "user_role")
public class UserRoleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "roleid")
    private Long roleId;

    @ManyToOne
    @JoinColumn(name = "userid")
    private UserEntity user;
}
