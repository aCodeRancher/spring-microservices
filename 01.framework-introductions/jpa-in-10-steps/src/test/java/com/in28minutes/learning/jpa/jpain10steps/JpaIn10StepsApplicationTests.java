package com.in28minutes.learning.jpa.jpain10steps;


import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertTrue;


@SpringBootTest
@ActiveProfiles("dev")
//Load the CommandLineRunners since the active profile is not test
public class JpaIn10StepsApplicationTests {


	//@Autowired
	//UsersRepository repository;

//@Autowired
	//UserDAOService db;

	@Test
	public void contextLoads() {
    	// Optional<User> user = repository.findById(1L);
    	// assertTrue(user.isPresent());
		// User user1 = new User("Jenny", "Doe");
    	 //long id = db.insert(user1);
    	// assertTrue(id >=0);


	}

}
