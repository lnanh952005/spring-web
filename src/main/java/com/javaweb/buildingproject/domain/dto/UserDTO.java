package com.javaweb.buildingproject.domain.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    @NotBlank(message = "không được để trống")
    private String fullname;

    @NotBlank(message = "không được để trống")
    private String username;

    @Size(min = 6,message = "vui long nhập mật khẩu tù 6 kí tự trở lên")
    private String password;

    private Long phone;

    private String email;

    private Long status;

}
