package com.in28minutes.learning.jpa.jpain10steps;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.in28minutes.learning.jpa.jpain10steps.db.users.ds.User;

//@Component
//@Profile("!test")
public class UserRepositoryCommandLineRunner {//implements CommandLineRunner{

	//private static final Logger log =
	//		LoggerFactory.getLogger(UserRepositoryCommandLineRunner.class);
	
	//@Autowired
	//private UsersRepository userRepository;
	
	//@Override
	//public void run(String... arg0) throws Exception {
	//	User user = new User(1,"Jill", "Admin");
		//userRepository.save(user);
	//	log.info("New User is created : " + user);
		
		//Optional<User> userWithIdOne = userRepository.findById(1L);
	//	log.info("User is retrived : " + userWithIdOne);

		//List<User> users = userRepository.findAll();
	//	log.info("All Users : " + users);
//	}
	
}