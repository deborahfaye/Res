package com.ibm.training.bootcamp.rest.casestudy.dao;

import java.util.List;
//import com.ibm.training.bootcamp.rest.casestudy.domain.User;
//import com.ibm.training.bootcamp.rest.casestudy.domain.Skills;
import com.ibm.training.bootcamp.rest.casestudy.domain.Assessment;
import com.ibm.training.bootcamp.rest.casestudy.domain.Search;
import com.ibm.training.bootcamp.rest.casestudy.domain.Skills;;

public interface AssessmentDao{

	public List<Assessment> findAll();

	public Assessment find(Long id);

	public List<Assessment> findByLevelExp(int monthsExp, int skillLevel);

	public void add(Assessment assessment);

	public void update(Assessment assessment);

	public List<Assessment>  findByLevel() ;

	public List<Skills> findBySkill();

	public List<Assessment> findjoinedtable(String skill_name);

	public List<Assessment> findAllJoined();

	public List<Assessment> findjoinedtable2(String firstName, String lastName);

    public List<Assessment> findjoinedtable3(String firstName, String lastName);

	public List<Assessment> findByLevelExp2(int monthsExp, int skillLevel);

//	public List<Assessment> findHistory();

//	public List<Assessment> findSkill_name();

//	public void addSkill(Assessment assessment1);

//	public void join();

//	public List<Assessment> Filter();

//	public List<Assessment> Filter(int monthsExp, int skillLevel);
	
	
}