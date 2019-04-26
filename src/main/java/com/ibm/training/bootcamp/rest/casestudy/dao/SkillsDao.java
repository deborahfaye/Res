package com.ibm.training.bootcamp.rest.casestudy.dao;

import java.util.List;
import com.ibm.training.bootcamp.rest.casestudy.domain.Skills;


public interface SkillsDao {

	public List<Skills> findAll();

	public Skills find(Long id);

	public List<Skills> findBySkill(String skill);

	public void add(Skills skil);

}
