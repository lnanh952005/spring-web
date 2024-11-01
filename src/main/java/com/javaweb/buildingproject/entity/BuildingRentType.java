package com.javaweb.buildingproject.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "buildingrenttype")
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BuildingEntity getBuilding() {
        return building;
    }

    public void setBuilding(BuildingEntity building) {
        this.building = building;
    }

    public RentTypeEntity getRentType() {
        return rentType;
    }

    public void setRentType(RentTypeEntity rentType) {
        this.rentType = rentType;
    }
}
