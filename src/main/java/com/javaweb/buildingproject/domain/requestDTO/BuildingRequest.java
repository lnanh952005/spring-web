package com.javaweb.buildingproject.domain.requestDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BuildingRequest {
    private String name;
    private Long numberOfbasement;
    private String address;

}
