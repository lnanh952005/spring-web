package com.javaweb.buildingproject.repository;

import com.javaweb.buildingproject.domain.entity.CompanyEntity;
import com.javaweb.buildingproject.domain.entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity,Long>, JpaSpecificationExecutor<UserEntity> {
    Page<UserEntity> findAll(Pageable pageable);

    Optional<UserEntity> findByUsername(String name);
    Optional<UserEntity> findByRefreshTokenAndUsername(String refreshToken, String username);
    Optional<UserEntity> findByUsernameOrEmail(String username, String email);
    List<UserEntity> findByCompany(CompanyEntity company);
}
