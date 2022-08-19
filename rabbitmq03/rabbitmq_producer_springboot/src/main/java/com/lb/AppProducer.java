package com.lb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AppProducer {

	public static void main(String[] args) {
		SpringApplication.run(AppProducer.class, args);
		System.out.println("启动成功");
	}

}
