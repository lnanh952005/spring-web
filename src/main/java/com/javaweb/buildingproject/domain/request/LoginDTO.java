package com.javaweb.buildingproject.domain.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginDTO {
    @NotBlank(message = "tài khoản không được để trống")
    private String username;
    @NotBlank(message = "mật khẩu không được để trống")
    private String password;
}
