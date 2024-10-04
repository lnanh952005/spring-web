package com.javaweb.buildingproject.DTO;

public class BuildingDTO {
    private String name;
    private Integer numberOfbasement;
    private String address;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getNumberOfbasement() {
        return numberOfbasement;
    }

    public void setNumberOfbasement(Integer numberOfbasement) {
        this.numberOfbasement = numberOfbasement;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}

