package com.kali.springboot.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/index")
public class IndexController {
    @GetMapping("introduce")
    public String index(){
        return "Hello Spring Boot~";
    }
}
