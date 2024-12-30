package com.cts.smartspend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
//@ComponentScan(basePackages = "com.cts.smartspend")
public class SmartSpendApplication {

	public static void main(String[] args) {

		SpringApplication.run(SmartSpendApplication.class, args);
	}

}
