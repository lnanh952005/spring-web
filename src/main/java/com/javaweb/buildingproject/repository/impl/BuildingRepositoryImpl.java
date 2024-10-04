package com.javaweb.buildingproject.repository.impl;

import com.javaweb.buildingproject.entity.BuildingEntity;
import com.javaweb.buildingproject.repository.BuildingRepository;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class BuildingRepositoryImpl implements BuildingRepository {
    static final String url = "jdbc:mysql://localhost:3306/estatebasic";
    static final String username = "root";
    static final String password = "123456";

    @Override
    public List<BuildingEntity> findAll() {

        String sql = "SELECT * FROM building b ";
        List<BuildingEntity> buildingList = new ArrayList<>();

        try(Connection con = DriverManager.getConnection(url,username,password);
            PreparedStatement statement = con.prepareStatement(sql);
            ResultSet result = statement.executeQuery();
        ){
            while(result.next()){
                BuildingEntity buildingentity = new BuildingEntity();
                buildingentity.setId(result.getInt("id"));
                buildingentity.setName(result.getString("name"));
                buildingentity.setStreet(result.getString("street"));
                buildingentity.setWard(result.getString("ward"));
                buildingentity.setDistricid(result.getInt("districtid"));
                buildingentity.setStructure(result.getString("structure"));
                buildingentity.setNumberofbasement(result.getInt("numberofbasement"));
                buildingentity.setFloorarea(result.getInt("floorarea"));
                buildingentity.setDirection(result.getString("direction"));
                buildingentity.setLevel(result.getString("level"));
                buildingentity.setRentprice(result.getInt("rentprice"));
                buildingentity.setRentpricedescription(result.getString("rentpricedescription"));
                buildingentity.setManagername(result.getString("managername"));
                buildingentity.setManagerphonenumber(result.getInt("managerphonenumber"));
                buildingList.add(buildingentity);
            }
        }
        catch (SQLException e){
            e.printStackTrace();
            System.out.println("CONNECTION FAILED");
        }
        return buildingList;
    }
}
