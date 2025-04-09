package com.dousheng.relation;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableDubbo
@SpringBootApplication
public class RelationApplication {

    public static void main(String[] args) {
        SpringApplication.run(RelationApplication.class, args);
        System.out.println("RelationApplication started");
    }

}
