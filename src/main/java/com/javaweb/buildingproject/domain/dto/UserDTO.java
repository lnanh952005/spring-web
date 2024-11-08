package com.javaweb.buildingproject.domain.dto;

import jakarta.validation.constraints.Email;
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

    private String userName;
    @Size(min = 8,message = "vui long nhập mật khẩu tù 8 kí tự trở lên")
    private String passWord;
    private String fullName;
    private Long phone;
    @Email(message = "vui long nhập email")
    private String email;
    private Long status;

}
