package com.javaweb.buildingproject.utils;

import com.javaweb.buildingproject.domain.ResponseDTO.RestResponse;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

@ControllerAdvice
public class FormatRestResponse implements ResponseBodyAdvice<Object> {

    @Override
    public boolean supports(MethodParameter returnType, Class converterType) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body,
                                  MethodParameter returnType,
                                  MediaType selectedContentType,
                                  Class selectedConverterType,
                                  ServerHttpRequest request,
                                  ServerHttpResponse response) {
        HttpServletResponse httpServletResponse = ((ServletServerHttpResponse) response).getServletResponse();
        RestResponse<Object> restResponse = new RestResponse<>();
        restResponse.setStatusCode(httpServletResponse.getStatus());
        if(httpServletResponse.getStatus() >= 400){
            return body;
        }
        else{
            restResponse.setError(null);
            restResponse.setData(body);
            restResponse.setMessage("Call api success");
        }
        return restResponse;
    }
}
