package com.javaweb.buildingproject.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RestLoginDTO {

    private String accessToken;
    private Userlogin userlogin;

    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Setter
    public static class Userlogin{
        private Long id;
        private String username;
        private String fullname;
    }
}
