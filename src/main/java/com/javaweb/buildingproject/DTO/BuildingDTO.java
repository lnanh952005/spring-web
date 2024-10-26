package com.javaweb.buildingproject.DTO;

public class BuildingDTO {
    private String name;
    private Long numberOfbasement;
    private String address;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getNumberOfbasement() {
        return numberOfbasement;
    }

    public void setNumberOfbasement(Long numberOfbasement) {
        this.numberOfbasement = numberOfbasement;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    public BuildingDTO() {
    }
    public BuildingDTO(String name, Long numberOfbasement, String address) {
        this.name = name;
        this.numberOfbasement = numberOfbasement;
        this.address = address;
    }
}

