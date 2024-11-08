package com.javaweb.buildingproject.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BuildingDTO {
    private String name;
    private String street;
    private String ward;
    private Long numberOfBasement;
    private Long floorArea;
    private String rentPriceDescription;
    private String managerName;
    private Long managerPhoneNumber;
}

