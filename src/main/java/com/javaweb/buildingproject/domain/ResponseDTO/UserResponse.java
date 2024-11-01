package com.javaweb.buildingproject.domain.ResponseDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {
    private String userName;
    private String passWord;
    private String fullName;
    private Long phone;
    private String email;
    private Long status;
}
