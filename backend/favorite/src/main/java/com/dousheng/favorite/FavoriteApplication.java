package com.dousheng.favorite;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableDubbo
@SpringBootApplication
public class FavoriteApplication {

	public static void main(String[] args) {
		SpringApplication.run(FavoriteApplication.class, args);
		System.out.println("FavoriteApplication started");
	}

}
