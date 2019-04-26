package com.ibm.training.bootcamp.rest.casestudy.restcontroller;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.commons.lang3.StringUtils;

import com.ibm.training.bootcamp.rest.casestudy.domain.Skills;
import com.ibm.training.bootcamp.rest.casestudy.service.SkillsService;
import com.ibm.training.bootcamp.rest.casestudy.service.SkillsServiceImpl;

@Path("/skills")
public class SkillsController {
	private SkillsService skillsService;
	
	public SkillsController() {
		this.skillsService = new SkillsServiceImpl();
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Skills> getSkills(@QueryParam("skill") String skill){
		
		try {
			List<Skills> skills;
			
			if(StringUtils.isAllBlank(skill)) {
				skills=skillsService.findAll();
			}else {
				skills=skillsService.findBySkill(skill);
			}
			return skills;
		}catch(Exception e) {
			throw new WebApplicationException(e);
		}
	}
	
	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Skills getSkill(@PathParam("id") String id) {
		
		try {
			Long longId = Long.parseLong(id);
			Skills skil = skillsService.find(longId);
			return skil;
		} catch(Exception e) {
			throw new WebApplicationException(e);
		}
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addSkill(Skills skil) {
		
		try {
			skillsService.add(skil);
			String result = "Skill saved: " + skil.getSkill();
			return Response.status(201).entity(result).build();
			
		}catch(Exception e){
			throw new WebApplicationException(e);
		}
	}
	
	
}