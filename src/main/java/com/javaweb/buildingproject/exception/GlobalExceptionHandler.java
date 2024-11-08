package com.javaweb.buildingproject.exception;

import com.javaweb.buildingproject.domain.ResponseDTO.RestResponse;
import com.javaweb.buildingproject.exception.custom.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.InvalidMediaTypeException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Collections;
import java.util.List;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = {
            MethodArgumentNotValidException.class,
            BadCredentialsException.class,
            InvalidMediaTypeException.class,
            UsernameNotFoundException.class
    })
    public ResponseEntity<RestResponse<Object>> handleValidation(MethodArgumentNotValidException ex) {
        BindingResult bindingResult = ex.getBindingResult();
        List<FieldError> fieldErrorList = bindingResult.getFieldErrors();
        List<String> errors = fieldErrorList.stream().map(o->o.getDefaultMessage()).toList();

        RestResponse<Object> restResponse = new RestResponse<>();
        restResponse.setError(errors.size() > 1 ? errors : errors.get(0)); //exception của @Valid
        restResponse.setStatusCode(HttpStatus.BAD_REQUEST.value());
        restResponse.setMessage("vui lòng nhập lại tk và mk");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(restResponse);
    }

    @ExceptionHandler(value = NotFoundException.class)
    public ResponseEntity<RestResponse<Object>> handleUserNotfound(NotFoundException notFoundException){
        RestResponse<Object> restResponse = new RestResponse<>();
        restResponse.setStatusCode(HttpStatus.BAD_REQUEST.value());
        restResponse.setError(null);
        restResponse.setMessage(notFoundException.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(restResponse);
    }
}