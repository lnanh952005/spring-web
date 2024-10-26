package com.javaweb.buildingproject.entity;

import jakarta.persistence.*;

@Entity(name = "buildingrenttype")
public class BuildingRentType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "buildingid")
    private BuildingEntity building;

    @ManyToOne
    @JoinColumn(name = "renttypeid")
    private RentTypeEntity rentType;
}
