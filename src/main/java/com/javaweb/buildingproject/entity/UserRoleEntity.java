package com.javaweb.buildingproject.entity;

import jakarta.persistence.*;
import org.apache.catalina.User;

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
