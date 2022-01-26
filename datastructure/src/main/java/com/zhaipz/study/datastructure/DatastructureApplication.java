package com.zhaipz.study.datastructure;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.zhaipz.study.datastructure.dao")
@SpringBootApplication
public class DatastructureApplication {

    public static void main(String[] args) {
        SpringApplication.run(DatastructureApplication.class, args);
    }

}
