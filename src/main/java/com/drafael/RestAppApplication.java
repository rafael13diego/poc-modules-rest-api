package com.drafael;

import com.drafael.entities.Role;
import com.drafael.entities.User;
import com.drafael.entities.UserInRole;
import com.drafael.repositories.RoleRepository;
import com.drafael.repositories.UserInRoleRepository;
import com.drafael.repositories.UserRepository;
import com.github.javafaker.Faker;
import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Random;

@SpringBootApplication
public class RestAppApplication implements ApplicationRunner {


	@Autowired
	private Faker faker;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private UserInRoleRepository userInRoleRepository;

	private static final Logger log = LoggerFactory.getLogger(RestAppApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(RestAppApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		Role[] roles = {new Role("ADMIN"), new Role("SUPPORT"), new Role("USER")};
		for (Role role: roles) {
			roleRepository.save(role);
		}
		for (int i = 0; i < 30; i++) {
			User user =new User();
			user.setUsername(faker.name().username());
			user.setPassword(faker.dragonBall().character());
			User created = userRepository.save(user);
			UserInRole userInRole = new UserInRole(created, roles[new Random().nextInt(3)]);
			userInRoleRepository.save(userInRole);

			log.info("User created, username {} password {} - role {}",created.getUsername(), created.getPassword(), userInRole.getRole().getName());
		}
	}
}
