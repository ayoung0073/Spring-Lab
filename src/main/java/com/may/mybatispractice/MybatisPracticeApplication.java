package com.may.mybatispractice;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "com.may.mybatispractice")
public class MybatisPracticeApplication {

    public static void main(String[] args) {
        SpringApplication.run(MybatisPracticeApplication.class, args);
    }

}
