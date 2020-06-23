package com.in28minutes.rest.webservices.restfulwebservices.user;

import java.util.Date;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFilter;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description="All details about the user.")
@Entity
//@JsonFilter("userFilter")
public class User {

	@Id
	@GeneratedValue
	private Integer id;

	@Size(min=2, message="Name should have atleast 2 characters")
	@ApiModelProperty(notes="Name should have atleast 2 characters", dataType = "String")
	private String name;

	@Past
	@ApiModelProperty(notes="Birth date should be in the past", dataType="Date")
	private Date birthDate;
	
	@OneToMany(mappedBy="user", cascade = CascadeType.REMOVE)
	//removing the user, also removing the posts
	private List<Post> posts;

	protected User() {

	}

	public User(Integer id, String name, Date birthDate) {
		super();
		this.id = id;
		this.name = name;
		this.birthDate = birthDate;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public List<Post> getPosts() {
		return posts;
	}

	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}

	@Override
	public String toString() {
		return String.format("User [id=%s, name=%s, birthDate=%s]", id, name, birthDate);
	}

}
