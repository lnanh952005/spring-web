package com.javaweb.buildingproject.domain.ResponseDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RestResponse<T> {
    private Integer statusCode;
    private Object error;
    private Object message;
    private T data;
}
