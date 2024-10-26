package com.javaweb.buildingproject.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity(name = "renttype")
public class RentTypeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "code")
    private String code;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "rentType" ,fetch = FetchType.LAZY)
    private List<BuildingRentType> buildingRentTypes;
}
