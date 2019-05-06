package com.ibm.training.bootcamp.rest.casestudy.service;

import java.util.List;

//import com.ibm.training.bootcamp.rest.casestudy.domain.User;
//import com.ibm.training.bootcamp.rest.casestudy.domain.Skills;
import com.ibm.training.bootcamp.rest.casestudy.domain.Assessment;
import com.ibm.training.bootcamp.rest.casestudy.domain.Search;
import com.ibm.training.bootcamp.rest.casestudy.domain.Skills;

public interface AssessmentService{

	public List<Assessment> findAll();

	public Assessment find(Long id);

	public List<Assessment> findByLevelExp(int monthsExp, int skillLevel);

	public void add(Assessment assessment);

//	public void upsert(Assessment assessment);

	public void update(Assessment assessment);

	public List<Assessment> findByLevel();

	public List<Skills> findBySkill();

//	public void addSkill(Assessment assessment1);
	
	
	
}