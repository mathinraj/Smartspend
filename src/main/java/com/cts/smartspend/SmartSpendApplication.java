package com.cts.smartspend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
public class SmartSpendApplication {

	public static void main(String[] args) {
		SpringApplication.run(SmartSpendApplication.class, args);
	}

}
