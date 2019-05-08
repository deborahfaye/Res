package com.ibm.training.bootcamp.rest.casestudy.restcontroller;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.ibm.training.bootcamp.rest.casestudy.domain.Assessment;
import com.ibm.training.bootcamp.rest.casestudy.domain.Skills;
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
	@Path("/history")
	@Produces (MediaType.APPLICATION_JSON)
	public List<Assessment> getListFilter(){
		
		try {
			List<Assessment> assessments;
			
				assessments = assessmentService.findByLevel();
	
			return assessments;
		}catch(Exception e) {
			throw new WebApplicationException(e);
		}
	}
	
	@GET
	@Path("/skill")
	@Produces (MediaType.APPLICATION_JSON)
	public List<Skills> getskillFilter(){
		
		try {
			List<Skills> assessments;
			
				assessments = assessmentService.findBySkill();
	
			return assessments;
		}catch(Exception e) {
			throw new WebApplicationException(e);
		}
	}
	
	@GET
	@Path("/joined/filter")
	@Produces (MediaType.APPLICATION_JSON)
	public List<Assessment> getsjoinedTable(@QueryParam("skill_name") String skill_name){
		
		try {
			List<Assessment> assessments;
			
				assessments = assessmentService.findjoinedtable( skill_name);
	
			return assessments;
		}catch(Exception e) {
			throw new WebApplicationException(e);
		}
	}
	
	
	@GET
	@Path("/joined/filter2")
	@Produces (MediaType.APPLICATION_JSON)
	public List<Assessment> getsjoinedTable2(@QueryParam("firstName") String firstName,
	             	@QueryParam("lastName") String lastName){
		try {
			List<Assessment> assessments;
			
				assessments = assessmentService.findjoinedtable2( firstName,lastName);
	
			return assessments;
		}catch(Exception e) {
			throw new WebApplicationException(e);
		}
	}
	@GET
	@Path("/joined/filter3")
	@Produces (MediaType.APPLICATION_JSON)
	public List<Assessment> getsjoinedTable3(@QueryParam("firstName") String firstName,
	             	@QueryParam("lastName") String lastName){
		try {
			List<Assessment> assessments;
			
				assessments = assessmentService.findjoinedtable3( firstName,lastName);
	
			return assessments;
		}catch(Exception e) {
			throw new WebApplicationException(e);
		}
	}
	
	@GET
	@Path("/joined")
	@Produces (MediaType.APPLICATION_JSON)
	public List<Assessment> getsjoinedTableAll(){
		
		try {
			List<Assessment> assessments;
			
				assessments = assessmentService.findAllJoined();
	
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
	@Produces (MediaType.APPLICATION_JSON)
	@Path("/filter2")
	public List<Assessment> getAssessments2(
			@QueryParam("monthsExp") int monthsExp,
			@QueryParam("skillLevel") int skillLevel){
		
		try {
			List<Assessment> assessments;
			
			
				assessments = assessmentService.findByLevelExp2(monthsExp, skillLevel);
			
			return assessments;
		}catch(Exception e) {
			throw new WebApplicationException(e);
		}
	}
	

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addUser(Assessment assessment) {
		 try {
			 assessmentService.add(assessment);
			 String result= "Assessment saved:" +assessment.getSkillLevel()+""+assessment.getMonthsExp()+""+assessment.getDevId()+""+assessment.getSkill_name();
		     return Response.status(201).entity(result).build();
		 }catch(Exception e) {
		 throw new WebApplicationException(e);
		 }
	}
	
@PUT
@Path("{id}")
@Consumes(MediaType.APPLICATION_JSON)
public Response updateAssessment(@PathParam("id") Long id, Assessment assessment) {
	
	try {
		assessment.setId(id);
		assessmentService.update(assessment);
		String result = "Assessment updated:" + assessment.getDevId()+""+assessment.getMonthsExp()+""+assessment.getSkillLevel()+""+assessment.getSkill_name();
	    return Response.status(200).entity(result).build();
	}catch (Exception e){
		throw new WebApplicationException(e);
	}
}
}
