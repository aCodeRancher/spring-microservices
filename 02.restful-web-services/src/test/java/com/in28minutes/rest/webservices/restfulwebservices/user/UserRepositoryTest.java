package com.in28minutes.rest.webservices.restfulwebservices.user;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest

//After each test, the transaction is rollback. For example, if a user is created in one test, the next
// test will not see the created user. It is because the create user test is rollback.

class UserRepositoryTest {

    @Autowired
    UserRepository repository;

    @Test
    public void createUser(){
        User user = new User(1005, "Alan", new Date());
        User savedUser =  repository.save(user);
        assertTrue(savedUser !=null);

    }

    @Test
    public void retrieveAllUsers(){
        List<User> users = repository.findAll();
        assertTrue(users.size()==3);
    }

    @Test
    public void retrieveUser(){
        Optional<User> user = repository.findById(10003);
        assertTrue(user.isPresent());
        assertTrue(user.get().getName().equals("Jam"));
    }

    @Test
    public void retrieveNonexistingUser(){
        Optional<User> user = repository.findById(10004);
        assertTrue(user.isEmpty());
     }

     @Test
    public void deleteUser(){
         List<User> allUsers = repository.findAll();
         assertTrue(allUsers.size()==3);
         assertTrue(repository.findById(10001).isPresent());
         assertTrue(repository.findById(10002).isPresent());
         assertTrue(repository.findById(10003).isPresent());
         assertTrue(repository.findById(1005).isEmpty());

         repository.deleteById(10002);
         assertTrue(repository.findById(10002).isEmpty());
         List<User> updatedList = repository.findAll();
         assertTrue(updatedList.size()==2);
     }
}