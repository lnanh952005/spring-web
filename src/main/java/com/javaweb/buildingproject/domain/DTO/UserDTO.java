package com.javaweb.buildingproject.domain.DTO;

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
    private String passWord;
    private String fullName;
    private Long phone;
    private String email;
    private Long status;

}
