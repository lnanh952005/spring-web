package com.javaweb.buildingproject.domain.Response;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonPropertyOrder({"statusCode","error","message","data"})
public class RestResponse<T> {
    private Integer statusCode;
    private Object error;
    private Object message;
    private T data;
}
