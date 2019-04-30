package com.ibm.training.bootcamp.rest.casestudy.service;

import java.util.List;


import org.apache.commons.lang3.StringUtils;

import com.ibm.training.bootcamp.rest.casestudy.dao.UserDao;
import com.ibm.training.bootcamp.rest.casestudy.dao.UserJdbcDaoImpl;
import com.ibm.training.bootcamp.rest.casestudy.domain.User;

public class UserServiceImpl implements UserService{
	
	UserDao userDao;
	
	public UserServiceImpl(){
		this.userDao=UserJdbcDaoImpl.getInstance();
	}
	
	@Override
	public List<User> findAll(){
		return userDao.findAll();
	}
	
	@Override
	public User find(Long devId) {
		return userDao.find(devId);
	}
	
	@Override
	public List<User> findByName(String firstName, String lastName){
		return userDao.findByName(firstName, lastName);
	}
	
	@Override 
	public void add(User user) {
		if(validate(user)) {
			userDao.add(user);
		}else {
			throw new IllegalArgumentException("Fields cannot be blank.");
		}
	}
	
	private boolean validate (User user) {
	
		 return !StringUtils.isAnyBlank(user.getFirstName(), user.getLastName());
	}
}
	
