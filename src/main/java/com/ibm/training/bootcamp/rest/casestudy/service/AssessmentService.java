package com.ibm.training.bootcamp.rest.casestudy.service;

import java.util.List;

import com.ibm.training.bootcamp.rest.casestudy.domain.User;
import com.ibm.training.bootcamp.rest.casestudy.domain.Skills;
import com.ibm.training.bootcamp.rest.casestudy.domain.Assessment;

public interface AssessmentService{

	public List<Assessment> findAll();

	public Assessment find(Long id);

	public List<Assessment> findByLevelExp(int monthsExp, int skillLevel);

	public void add(Assessment assessment);
	
	
	
}