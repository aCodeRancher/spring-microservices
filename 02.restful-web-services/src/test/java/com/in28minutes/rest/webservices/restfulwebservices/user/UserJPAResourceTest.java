package com.in28minutes.rest.webservices.restfulwebservices.user;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(UserJPAResource.class)
@AutoConfigureMockMvc
class UserJPAResourceTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    UserRepository userRepository;

    @MockBean
    PostRepository postRepository;

    @Captor
    ArgumentCaptor<User> captorUser;

    @Captor
    ArgumentCaptor<Post> captorPost;
    @Test
    public void retrieveAllUser() throws Exception{
         List<User> userslist = new ArrayList<>();
         String strdate = "1998-02-04 11:40:00";
         SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
         Date birthdate = dateFormat.parse(strdate);
         User user = new User(1, "Alan", birthdate);
         userslist.add(user);
         when(userRepository.findAll()).thenReturn(userslist);
         MvcResult result = mockMvc.perform(get("/jpa/users")).andReturn();
         String output = result.getResponse().getContentAsString();
         String expected = "[{\"id\":1,\"name\":\"Alan\",\"birthDate\":\"1998-01-04T16:40:00.000+0000\",\"posts\":null}]";
         assertTrue(output.contains(expected));
         verify(userRepository,times(1)).findAll();
    }

    @Test
    public void retrieveUser() throws Exception{
        String strdate = "1998-02-04 11:40:00";
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        Date birthdate = dateFormat.parse(strdate);
        User user = new User(1, "Alan", birthdate);
        Optional<User> userOptional = Optional.of(user);
        when(userRepository.findById(1)).thenReturn(userOptional);
        MvcResult result = mockMvc.perform(get("/jpa/users/1")).andReturn();
        String output = result.getResponse().getContentAsString();
        String expected =
                "{\"id\":1,\"name\":\"Alan\",\"birthDate\":\"1998-01-04T16:40:00.000+0000\",\"posts\":null,\"_links\":{\"all-users\":{\"href\":\"http://localhost/jpa/users\"}}}";
        assertTrue(output.contains(expected));
        verify(userRepository,times(1)).findById(1);
    }

    @Test
    public void retrieveUser_nofound() throws Exception{
        Optional<User> userOptional = Optional.empty();
        when(userRepository.findById(1)).thenReturn(userOptional);
        mockMvc.perform(get("/jpa/users/1"))
                        .andExpect(status().isNotFound());
        verify(userRepository,times(1)).findById(1);
    }

    @Test
    public void deleteUser() throws Exception{
         doNothing().when(userRepository).deleteById(1);
         mockMvc.perform(delete("/jpa/users/1"))
                 .andExpect(status().isOk());
         verify(userRepository,times(1)).deleteById(1);

    }

    @Test
    public void createUser() throws Exception{
        String strdate = "1998-02-04 11:40:00";
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        Date birthdate = dateFormat.parse(strdate);
        User user = new User(1, "Alan", birthdate);
        String input = asJson(user);
        when(userRepository.save(any())).thenReturn(user);
        mockMvc.perform(post("/jpa/users")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON).content(input))
                .andExpect(status().isCreated());
        verify(userRepository,times(1)).save(captorUser.capture());
    }

    private String asJson(Object obj){
        try{
             return new ObjectMapper().writeValueAsString(obj);
        }
        catch(Exception e){
            throw new RuntimeException();
        }
    }

    @Test
    public void retrieveUserPosts() throws Exception{
        String strdate = "1998-02-04 11:40:00";
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        Date birthdate = dateFormat.parse(strdate);
        User user = new User(1, "Alan", birthdate);
        Post post =  new Post(101, "Alan has a good post", user);
        List<Post> posts = new ArrayList<>();
        posts.add(post);
        user.setPosts(posts);
        Optional<User> userOptional = Optional.of(user);
        when(userRepository.findById(1)).thenReturn(userOptional);
        MvcResult result = mockMvc.perform(get("/jpa/users/1/posts")).andReturn();
        String output = result.getResponse().getContentAsString();
        String expected = "[{\"id\":101,\"description\":\"Alan has a good post\"}]";
        assertTrue(output.contains(expected));
        verify(userRepository,times(1)).findById(1);
    }

    @Test
    public void createPost() throws Exception{
        String strdate = "1998-02-04 11:40:00";
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        Date birthdate = dateFormat.parse(strdate);
        User user = new User(1, "Alan", birthdate);
        Post post =  new Post(101, "Alan has a good post", user);
        String postasJson = asJson(post);
        Optional<User> userOptional = Optional.of(user);
        when(userRepository.findById(1)).thenReturn(userOptional);
        when(postRepository.save(any())).thenReturn(post);
        mockMvc.perform(post("/jpa/users/1/posts")
                                        .accept(MediaType.APPLICATION_JSON)
                                         .contentType(MediaType.APPLICATION_JSON)
                                       .content(postasJson))
                                       .andExpect(status().isCreated());
        verify(userRepository,times(1)).findById(1);
        verify(postRepository,times(1)).save(captorPost.capture());
    }
}