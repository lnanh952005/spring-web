package com.javaweb.buildingproject.repository;

import com.javaweb.buildingproject.domain.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity,Long> {
    Optional<UserEntity> findByusername(String name);
    Optional<UserEntity> findByemail(String email);
    boolean existsByusername(String username);
    boolean existsByemail(String email);
}
