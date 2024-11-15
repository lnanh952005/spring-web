package com.javaweb.buildingproject.domain.dto;

import com.javaweb.buildingproject.domain.Response.Meta;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResultPaginationDTO {
    private Meta meta;
    private Object result;
}
