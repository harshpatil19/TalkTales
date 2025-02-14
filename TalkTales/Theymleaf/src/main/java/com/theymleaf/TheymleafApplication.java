package com.theymleaf;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages="com.thyemleaf.Controller")
public class TheymleafApplication {

	public static void main(String[] args) {
		SpringApplication.run(TheymleafApplication.class, args);
	}

}
