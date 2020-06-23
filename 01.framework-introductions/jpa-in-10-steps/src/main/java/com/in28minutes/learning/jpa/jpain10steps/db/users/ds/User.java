package com.in28minutes.learning.jpa.jpain10steps.db.users.ds;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;

//Table - User
@Entity
@Getter
@Setter
@AllArgsConstructor
@ToString
public class User {

	@Id
   private int id;

	private String name;

	private String role;

	public User() {

	}




}
