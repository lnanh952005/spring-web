package com.javaweb.buildingproject.domain.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Valid
public class LoginDTO {
    @NotBlank(message = "tài khoản không được để trống")
    private String userName;
    @NotBlank(message = "mật khẩu không được để trống")
    private String passWord;
}
