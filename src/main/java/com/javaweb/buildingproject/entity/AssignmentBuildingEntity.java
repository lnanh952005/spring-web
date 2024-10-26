package com.javaweb.buildingproject.entity;

import jakarta.persistence.*;

@Entity(name = "assignmentbuilding")
public class AssignmentBuildingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "staffid")
    private Long staffId;

    @Column(name = "buildingid")
    private Long buildingId;

}
