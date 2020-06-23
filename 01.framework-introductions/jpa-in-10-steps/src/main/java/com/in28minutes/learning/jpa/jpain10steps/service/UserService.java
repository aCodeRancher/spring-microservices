package com.in28minutes.learning.jpa.jpain10steps.service;

import com.in28minutes.learning.jpa.jpain10steps.db.users.ds.User;
import com.in28minutes.learning.jpa.jpain10steps.db.users.dao.UsersRepository;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Getter
public class UserService {

    private UsersRepository repository;

    public UserService (UsersRepository repository)
    {
        this.repository = repository;
    }
    @Transactional
    public void save(){
        User user = new User( 1,"Jill", "Admin");
        repository.save(user);
    }

    public void list(){
       List<User> users = repository.findAll();
       System.out.println(users.get(0).getName());
    }
}

