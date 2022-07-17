package com.xdx97.blog;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan({"com.xdx97.blog.mapper"})
public class App {
    public static void main(String[] args) {

        SpringApplication.run(App.class, args);
    }

}
