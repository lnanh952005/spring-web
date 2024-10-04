package com.javaweb.buildingproject.entity;

public class BuildingEntity {
    private Integer id;
    private String name;
    private String street;
    private String ward;
    private Integer districid;
    private String structure;
    private Integer numberofbasement;
    private Integer floorarea;
    private String direction;
    private String level;
    private Integer rentprice;
    private String rentpricedescription;
    private String managername;
    private Integer managerphonenumber;
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getWard() {
        return ward;
    }

    public void setWard(String ward) {
        this.ward = ward;
    }

    public Integer getDistricid() {
        return districid;
    }

    public void setDistricid(Integer districid) {
        this.districid = districid;
    }

    public String getStructure() {
        return structure;
    }

    public void setStructure(String structure) {
        this.structure = structure;
    }

    public Integer getNumberofbasement() {
        return numberofbasement;
    }

    public void setNumberofbasement(Integer numberofbasement) {
        this.numberofbasement = numberofbasement;
    }

    public Integer getFloorarea() {
        return floorarea;
    }

    public void setFloorarea(Integer floorarea) {
        this.floorarea = floorarea;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public Integer getRentprice() {
        return rentprice;
    }

    public void setRentprice(Integer rentprice) {
        this.rentprice = rentprice;
    }

    public String getRentpricedescription() {
        return rentpricedescription;
    }

    public void setRentpricedescription(String rentpricedescription) {
        this.rentpricedescription = rentpricedescription;
    }

    public String getManagername() {
        return managername;
    }

    public void setManagername(String managername) {
        this.managername = managername;
    }

    public Integer getManagerphonenumber() {
        return managerphonenumber;
    }

    public void setManagerphonenumber(Integer managerphonenumber) {
        this.managerphonenumber = managerphonenumber;
    }
}
