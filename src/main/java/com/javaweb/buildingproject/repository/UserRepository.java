package com.javaweb.buildingproject.repository;

import com.javaweb.buildingproject.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<UserEntity,Long> {
    List<UserEntity> findByuserName(String name);
    List<UserEntity> findByemail(String email);
    boolean existsByuserName(String username);
    boolean existsByemail(String email);
}
