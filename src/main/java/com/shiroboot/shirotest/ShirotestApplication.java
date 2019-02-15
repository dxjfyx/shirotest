package com.shiroboot.shirotest;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.shiroboot.shirotest.dao")
public class ShirotestApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShirotestApplication.class, args);
    }

}

