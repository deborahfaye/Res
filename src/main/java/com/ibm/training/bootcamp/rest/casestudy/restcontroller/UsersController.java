package com.ibm.training.bootcamp.rest.casestudy.restcontroller;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.commons.lang3.StringUtils;

import com.ibm.training.bootcamp.rest.casestudy.domain.User;
import com.ibm.training.bootcamp.rest.casestudy.service.UserService;
import com.ibm.training.bootcamp.rest.casestudy.service.UserServiceImpl;

@Path("/users")
public class UsersController {
	
	private UserService userService;
	
	public UsersController() {
		this.userService = new UserServiceImpl();
	}
	
	@GET
	@Produces (MediaType.APPLICATION_JSON)
	public List<User> getUsers(
			@QueryParam("firstName") String firstName,
			@QueryParam("lastName") String lastName){
		
		try {
			List<User> users;
			
			if(StringUtils.isAllBlank(firstName, lastName)) {
				users = userService.findAll();
			}else {
				users = userService.findByName(firstName, lastName);
			}
			return users;
		}catch(Exception e) {
			throw new WebApplicationException(e);
		}
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addUser(User user) {
		 try {
			 userService.add(user);
			 String result= "User saved:" +user.getFirstName()+ ""+user.getMiddleName()+""+user.getLastName()+""+user.getBirthDate()+""+user.getPosition();
		     return Response.status(201).entity(result).build();
		 }catch(Exception e) {
		 throw new WebApplicationException(e);
		 }
	}
}