package com.hda.dev;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@Component
@SpringBootApplication
public class HealthDiagnosisApp {

	public static void main(String[] args) {
		SpringApplication.run(HealthDiagnosisApp.class, args);
	}

}
