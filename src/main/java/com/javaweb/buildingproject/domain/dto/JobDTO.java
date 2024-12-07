package com.javaweb.buildingproject.domain.dto;

import com.javaweb.buildingproject.enums.LevelEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class JobDTO {
    private Long id;

    private String name;

    private String location;

    private Double salary;

    private Integer quantity;

    private LevelEnum level;

    private String description;

    private CompanyDTO company;

    private Instant createdAt;

    private Instant updatedAt;

    private String createdBy;

    private String updatedBy;
}
