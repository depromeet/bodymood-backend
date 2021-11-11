package com.depromeet.dgdg;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@ConfigurationPropertiesScan(basePackageClasses = {DgdgBackendApplication.class})
@SpringBootApplication
public class DgdgBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(DgdgBackendApplication.class, args);
	}

}
