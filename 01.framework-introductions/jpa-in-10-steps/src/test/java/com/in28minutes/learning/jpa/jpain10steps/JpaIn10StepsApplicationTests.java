package com.in28minutes.learning.jpa.jpain10steps;


import com.in28minutes.learning.jpa.jpain10steps.entity.User;
import com.in28minutes.learning.jpa.jpain10steps.service.UserDAOService;
import com.in28minutes.learning.jpa.jpain10steps.service.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertTrue;


@SpringBootTest
@ActiveProfiles("dev")
//Load the CommandLineRunners since the active profile is not test
public class JpaIn10StepsApplicationTests {


	@Autowired
	UserRepository repository;

	@Autowired
	UserDAOService dao;

	@Test
	public void contextLoads() {
    	 Optional<User> user = repository.findById(1L);
    	 assertTrue(user.isPresent());
		 User user1 = new User("Jenny", "Doe");
    	 long id = dao.insert(user1);
    	 assertTrue(id >=0);


	}

}
