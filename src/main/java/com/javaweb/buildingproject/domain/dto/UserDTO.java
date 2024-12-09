package com.javaweb.buildingproject.domain.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDTO {
    private Long id;

    @NotBlank(message = "không được để trống")
    private String fullname;

    @NotBlank(message = "không được để trống")
    private String username;

    @Size(min = 6,message = "vui long nhập mật khẩu tù 6 kí tự trở lên")
    private String password;

    private Long phone;

    private String email;

    private Long age;

    private CompanyDTO company;

    private Instant createdAt;

    private Instant updatedAt;

    private String createdBy;

    private String updatedBy;
}
