package com.in28minutes.learning.jpa.jpain10steps;

import com.in28minutes.learning.jpa.jpain10steps.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JpaIn10StepsApplication  implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(JpaIn10StepsApplication.class, args);
	}

	@Autowired
	private UserService service;

	@Override
	public void run(String... args) {

		service.save( );
		//service.list();
	}
}
