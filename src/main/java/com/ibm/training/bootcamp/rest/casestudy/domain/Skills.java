package com.ibm.training.bootcamp.rest.casestudy.domain;

public class Skills {

	Long id;
	private String skill;

	public Skills() {

	}

//	public Skills(String skill) {
//		this(null, skill);
//	}

	public Skills(Long id, String skill) {
		this.id = id;
		this.skill = skill;
	}
	
	public Skills( String skill) {
		this.skill = skill;
	}

	public Long getid() {
		return id;
	}

	public void setid(Long id) {
		this.id = id;
	}

	public String getSkill() {
		return skill;
	}

	public void setSkill(String skill) {
		this.skill = skill;
	}

}