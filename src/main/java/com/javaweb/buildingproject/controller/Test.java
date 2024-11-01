package com.javaweb.buildingproject.controller;

import lombok.Getter;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/test")
public class Test {

    @GetMapping
    public String test(){
        return "index.html";
    }
}
