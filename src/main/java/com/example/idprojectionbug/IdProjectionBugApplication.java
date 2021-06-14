package com.example.idprojectionbug;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories
public class IdProjectionBugApplication {

	public static void main(String[] args) {
		SpringApplication.run(IdProjectionBugApplication.class, args);
	}

}
