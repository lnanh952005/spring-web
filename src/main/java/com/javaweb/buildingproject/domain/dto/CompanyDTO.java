package com.javaweb.buildingproject.domain.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
@Getter
@Setter
public class CompanyDTO {
    @NotBlank(message = "không được để trống")
    private String name;

    private String address;

    private String description;

    private String logo;

    private Instant createdAt;

    private Instant updatedAt;

    private String createdBy;

    private String updatedBy;
}
