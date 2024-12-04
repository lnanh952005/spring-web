package com.javaweb.buildingproject.repository;

import com.javaweb.buildingproject.domain.entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity,Long>, JpaSpecificationExecutor<UserEntity> {
    Page<UserEntity> findAll(Pageable pageable);
    Optional<UserEntity> findByusername(String name);
    Optional<UserEntity> findByemail(String email);
    boolean existsByusername(String username);
    boolean existsByemail(String email);

    Optional<UserEntity> findByRefreshTokenAndUsername(String refreshToken, String username);
    Optional<UserEntity> findByUsernameOrEmail(String username, String email);
}
