package org.lafresca.lafrescabackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class LaFrescaBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(LaFrescaBackendApplication.class, args);
	}

}
