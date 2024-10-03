package com.javaweb.buildingproject;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class controller {

    @GetMapping(value = "/hello")
    public String hello(){
        return "hello";
    }
}
