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

import com.ibm.training.bootcamp.rest.casestudy.domain.User;
import com.ibm.training.bootcamp.rest.casestudy.service.UserService;
import com.ibm.training.bootcamp.rest.casestudy.service.UserServiceImpl;
import com.ibm.training.bootcamp.rest.casestudy.domain.Skills;
import com.ibm.training.bootcamp.rest.casestudy.service.SkillsService;
import com.ibm.training.bootcamp.rest.casestudy.service.SkillsServiceImpl;
import com.ibm.training.bootcamp.rest.casestudy.domain.Assessment;
import com.ibm.training.bootcamp.rest.casestudy.service.AssessmentService;
import com.ibm.training.bootcamp.rest.casestudy.service.AssessmentServiceImpl;


@Path("/assessment")
public class AssessmentController{
	
	private AssessmentService assessmentService;
	
	public AssessmentController() {
		this.assessmentService= new AssessmentServiceImpl();
	}
	
	@GET
	@Produces (MediaType.APPLICATION_JSON)
	public List<Assessment> getAssessmentsAll(){
		
		try {
			List<Assessment> assessments;
			
				assessments = assessmentService.findAll();

			
			return assessments;
		}catch(Exception e) {
			throw new WebApplicationException(e);
		}
	}
	
	
	@GET
	@Produces (MediaType.APPLICATION_JSON)
	@Path("/filter")
	public List<Assessment> getAssessments(
			@QueryParam("monthsExp") int monthsExp,
			@QueryParam("skillLevel") int skillLevel){
		
		try {
			List<Assessment> assessments;
			
			
				assessments = assessmentService.findByLevelExp(monthsExp, skillLevel);
			
			return assessments;
		}catch(Exception e) {
			throw new WebApplicationException(e);
		}
	}
	
	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Assessment getAssessment(@PathParam("id") String id) {
		try {
			Long longid = Long.parseLong(id) ;
			Assessment assessment = assessmentService.find(longid);
			
			return assessment;
		}catch(Exception e) {
			throw new WebApplicationException(e);
		}
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addUser(Assessment assessment) {
		 try {
			 assessmentService.add(assessment);
			 String result= "Assessment saved:" +assessment.getSkillId()+ ""+assessment.getSkillLevel()+""+assessment.getMonthsExp()+""+assessment.getDevId();
		     return Response.status(201).entity(result).build();
		 }catch(Exception e) {
		 throw new WebApplicationException(e);
		 }
	}
}
