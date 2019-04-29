package com.ibm.training.bootcamp.rest.casestudy.service;

import java.util.List;

import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;

import com.ibm.training.bootcamp.rest.casestudy.dao.UserDao;
import com.ibm.training.bootcamp.rest.casestudy.dao.UserJdbcDaoImpl;
import com.ibm.training.bootcamp.rest.casestudy.domain.User;
import com.ibm.training.bootcamp.rest.casestudy.dao.SkillsDao;
import com.ibm.training.bootcamp.rest.casestudy.dao.SkillsJdbcDaoImpl;
import com.ibm.training.bootcamp.rest.casestudy.domain.Skills;
import com.ibm.training.bootcamp.rest.casestudy.dao.AssessmentDao;
import com.ibm.training.bootcamp.rest.casestudy.dao.AssessmentJdbcDaoImpl;
import com.ibm.training.bootcamp.rest.casestudy.domain.Assessment;

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
	public void add(Assessment assessment) {


			assessmentDao.add(assessment);
		
	}
}
	