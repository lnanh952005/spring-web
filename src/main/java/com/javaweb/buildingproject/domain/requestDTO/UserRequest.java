package com.javaweb.buildingproject.domain.requestDTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class UserRequest {
    private String userName;

    @Size(min = 8,message = "mật khẩu từ 8 ký tự trở lên")
    private String passWord;
    private String fullName;
    private Long phone;

    @Email(message = "vui lòng nhập email")
    private String email;
    private Long status;

}
