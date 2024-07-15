package com.zhl.ialcohol;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@MapperScan("com.zhl.ialcohol.mapper")
@ComponentScan(basePackages = {"com.zhl.ialcohol"})
@SpringBootApplication
public class IAlcoholApplication {

    public static void main(String[] args) {
        SpringApplication.run(IAlcoholApplication.class, args);
    }

}
