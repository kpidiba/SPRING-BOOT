package com.jpa.crud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages ="com.jpa")
public class BootrestapiApplication {

	public static void main(String[] args) {
		SpringApplication.run(BootrestapiApplication.class, args);
	}

}
