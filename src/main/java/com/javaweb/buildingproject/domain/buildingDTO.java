package com.javaweb.buildingproject.domain;

public class buildingDTO {
    private String name;
    private String numberOfbasement;
    private String address;

    public String getNumberOfbasement() {
        return numberOfbasement;
    }

    public void setNumberOfbasement(String numberOfbasement) {
        this.numberOfbasement = numberOfbasement;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}

