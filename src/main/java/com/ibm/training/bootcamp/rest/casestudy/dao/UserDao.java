package com.ibm.training.bootcamp.rest.casestudy.dao;

import java.util.List;
import com.ibm.training.bootcamp.rest.casestudy.domain.User;

public interface UserDao{

	public List<User> findAll();

	public void add(User user);

	public List<User> findByName(String firstName, String lastName);
	
	
	
}