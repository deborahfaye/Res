package com.ibm.training.bootcamp.rest.casestudy.service;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.ibm.training.bootcamp.rest.casestudy.dao.SkillsDao;
import com.ibm.training.bootcamp.rest.casestudy.dao.SkillsJdbcDaoImpl;
import com.ibm.training.bootcamp.rest.casestudy.domain.Skills;


public class SkillsServiceImpl implements SkillsService{
	
	SkillsDao skillsDao;
	
	
	public SkillsServiceImpl() {
		this.skillsDao=SkillsJdbcDaoImpl.getInstance();
	}
	
	@Override
	public List<Skills> findAll(){
		return skillsDao.findAll();
	}
	
	
	@Override 
	public List<Skills> findBySkill(String skill){
		return skillsDao.findBySkill(skill);
	}
	
	@Override
	public void add(Skills skil) {
		if (validate(skil)) {
			skillsDao.add(skil);
		} else {
			throw new IllegalArgumentException("Field skill cannot be blank.");
		}
	}

	 private boolean validate(Skills skil) {
		 return !StringUtils.isAnyBlank(skil.getSkill());
	 }
	
}