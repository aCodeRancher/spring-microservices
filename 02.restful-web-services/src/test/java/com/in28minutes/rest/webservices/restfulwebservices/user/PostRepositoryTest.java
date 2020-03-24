package com.in28minutes.rest.webservices.restfulwebservices.user;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;


@DataJpaTest
class PostRepositoryTest {

    @Autowired
    PostRepository postRepository;

    @Autowired
    UserRepository userRepository;

    User user;

    Post post;
    @BeforeEach
    public void setUp(){
        user =  userRepository.findById(10002).get();
        post = new Post();
        post.setId(11003);
        post.setDescription("Spring boot is good.");
        post.setUser(user);
    }

    @Test
    public void createPost(){
      postRepository.save(post);
      assertTrue( postRepository.findById(11003) !=null);

    }
}