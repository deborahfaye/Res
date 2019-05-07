package com.ibm.training.bootcamp.rest.casestudy.service;

import java.util.List;

import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;

//import com.ibm.training.bootcamp.rest.casestudy.dao.UserDao;
//import com.ibm.training.bootcamp.rest.casestudy.dao.UserJdbcDaoImpl;
//import com.ibm.training.bootcamp.rest.casestudy.domain.User;
//import com.ibm.training.bootcamp.rest.casestudy.dao.SkillsDao;
//import com.ibm.training.bootcamp.rest.casestudy.dao.SkillsJdbcDaoImpl;
//import com.ibm.training.bootcamp.rest.casestudy.domain.Skills;
import com.ibm.training.bootcamp.rest.casestudy.dao.AssessmentDao;
import com.ibm.training.bootcamp.rest.casestudy.dao.AssessmentJdbcDaoImpl;
import com.ibm.training.bootcamp.rest.casestudy.domain.Assessment;
import com.ibm.training.bootcamp.rest.casestudy.domain.Search;
import com.ibm.training.bootcamp.rest.casestudy.domain.Skills;

public class AssessmentServiceImpl implements AssessmentService{
	
	
	AssessmentDao assessmentDao;
	
	
	public AssessmentServiceImpl() {
		this.assessmentDao=AssessmentJdbcDaoImpl.getInstance();
	}
	
	@Override
	public List<Assessment> findAll(){
		return assessmentDao.findAll();
	}
	
	@Override
	public Assessment find(Long id) {
		return assessmentDao.find(id);
	}
	
	@Override
	public List<Assessment> findByLevelExp(int monthsExp, int skillLevel){
		return assessmentDao.findByLevelExp(monthsExp, skillLevel);
	}
	@Override
	public List<Assessment> findByLevelExp2(int monthsExp, int skillLevel){
		return assessmentDao.findByLevelExp2(monthsExp, skillLevel);
	}
	
	@Override
	public void add(Assessment assessment) {
			assessmentDao.add(assessment);
	}
	
	@Override
	public void update(Assessment assessment) {
				assessmentDao.update(assessment);
	}	
	
	@Override
	public List<Assessment> findByLevel(){
		return assessmentDao.findByLevel();
	}
	
	@Override
	public List<Skills> findBySkill(){
		return assessmentDao.findBySkill();
	}
	
	@Override
	public List<Assessment> findjoinedtable(String skill_name){
		return assessmentDao.findjoinedtable(skill_name);
	}
	
	@Override
	public List<Assessment> findAllJoined(){
		return assessmentDao.findAllJoined();
	}
	
	@Override
	public List<Assessment> findjoinedtable2(String firstName, String lastName){
		return assessmentDao.findjoinedtable2(firstName,lastName);
	}
	@Override
	public List<Assessment> findjoinedtable3(String firstName, String lastName){
		return assessmentDao.findjoinedtable3(firstName,lastName);
	}
}
	
