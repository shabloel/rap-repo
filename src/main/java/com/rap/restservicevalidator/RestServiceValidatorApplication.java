package com.rap.restservicevalidator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages={"com.rap.restservicevalidator"})
public class RestServiceValidatorApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestServiceValidatorApplication.class, args);
	}

}
