package com.in28minutes.learning.jpa.jpain10steps.db.users.dao;

import com.in28minutes.learning.jpa.jpain10steps.db.users.ds.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<User, Integer>{

}
