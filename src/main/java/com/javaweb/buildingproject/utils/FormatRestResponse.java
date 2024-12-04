package com.javaweb.buildingproject.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.javaweb.buildingproject.annotation.ApiMessage;
import com.javaweb.buildingproject.response.RestResponse;
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
        if (body instanceof String) {
            return body;
        }
        RestResponse restResponse = new RestResponse();
        restResponse.setStatusCode(((ServletServerHttpResponse) response).getServletResponse().getStatus());
        restResponse.setError(null);
        restResponse.setData(body);
        ApiMessage apiMessage = returnType.getMethodAnnotation(ApiMessage.class);
        restResponse.setMessage(apiMessage == null ? "call api successfully" : apiMessage.value());
        return restResponse;
    }
}
