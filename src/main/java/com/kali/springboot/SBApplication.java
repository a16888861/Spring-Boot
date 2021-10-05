package com.kali.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;

@SpringBootApplication
@ComponentScans(value = {
        @ComponentScan("com.kali.springboot.controller"),
        @ComponentScan("com.kali.springboot.service"),
        @ComponentScan("com.kali.springboot.dao"),
        @ComponentScan("com.kali.springboot.config"),
})
public class SBApplication {

    public static void main(String[] args) {
        SpringApplication.run(SBApplication.class, args);
    }

}
