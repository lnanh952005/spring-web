package com.javaweb.buildingproject.repository;

import com.javaweb.buildingproject.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity,Long> {
    Optional<UserEntity> findByuserName(String name);
    Optional<UserEntity> findByemail(String email);
    boolean existsByuserName(String username);
    boolean existsByemail(String email);
}
