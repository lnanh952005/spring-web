package com.javaweb.buildingproject.exception;

import com.javaweb.buildingproject.exception.custom.MissingValueException;
import com.javaweb.buildingproject.response.RestResponse;
import com.javaweb.buildingproject.exception.custom.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.oauth2.jwt.JwtException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import org.springframework.web.bind.MissingRequestValueException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<RestResponse> handleValidation(MethodArgumentNotValidException ex) {
        BindingResult bindingResult = ex.getBindingResult();
        List<FieldError> fieldErrorList = bindingResult.getFieldErrors();
        List<String> errors = fieldErrorList.stream().map(o->o.getDefaultMessage()).toList();

        RestResponse restResponse = new RestResponse();
        restResponse.setError(errors.size() > 1 ? errors : errors.get(0)); //exception của @Valid
        restResponse.setStatusCode(HttpStatus.BAD_REQUEST.value());
        restResponse.setMessage(ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(restResponse);
    }

    @ExceptionHandler(value = {
            NotFoundException.class,
            BadCredentialsException.class
    })
    public ResponseEntity<RestResponse> handleUserNotfound(Exception exception){
        RestResponse restResponse = new RestResponse();
        restResponse.setStatusCode(HttpStatus.BAD_REQUEST.value());
        restResponse.setError(exception.getMessage());
        restResponse.setMessage("Exception occured...");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(restResponse);
    }

    @ExceptionHandler(value = JwtException.class)
    public ResponseEntity<RestResponse> handleJwtException(JwtException exception){
        RestResponse restResponse = new RestResponse();
        restResponse.setStatusCode(HttpStatus.UNAUTHORIZED.value());
        restResponse.setMessage("lỗi jwt");
        restResponse.setError(exception.getMessage());
        return ResponseEntity.badRequest().body(restResponse);
    }

    @ExceptionHandler(value = MissingRequestValueException.class)
    public ResponseEntity<RestResponse> handleMissingRequestCookieException(Exception exception){
        RestResponse restResponse = new RestResponse();
        restResponse.setStatusCode(HttpStatus.BAD_REQUEST.value());
        restResponse.setError(exception.getMessage());
        restResponse.setMessage("exception occured...");
        return ResponseEntity.badRequest().body(restResponse);
    }
}
