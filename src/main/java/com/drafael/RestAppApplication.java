package com.drafael;

import com.drafael.entities.User;
import com.drafael.repositories.UserRepository;
import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RestAppApplication implements ApplicationRunner {


	@Autowired
	private Faker faker;

	@Autowired
	private UserRepository userRepository;

	public static void main(String[] args) {
		SpringApplication.run(RestAppApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		for (int i = 0; i < 30; i++) {
			User user =new User();
			user.setUsername(faker.name().username());
			user.setPassword(faker.dragonBall().character());
			userRepository.save(user);
		}
	}
}
