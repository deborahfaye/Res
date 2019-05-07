package com.ibm.training.bootcamp.rest.casestudy.domain;

public class Assessment {

	Long id;
    private Long devId;
	private Long skillId;
	private int monthsExp;
	private int skillLevel;
	private String skill_name;

	private String firstName;
	private String lastName;

	public Assessment() {

	}

	public Assessment(int monthsExp, int skillLevel, Long devId,  String skill_name , Long skillId) {
		this(null,monthsExp, skillLevel, devId,  skill_name, skillId );
	}

	public Assessment(Long id, int monthsExp, int skillLevel, Long devId,  String skill_name, Long skillId) {
		this.id = id;
		this.devId = devId;
		this.skillId = skillId;
		this.monthsExp = monthsExp;
		this.skillLevel = skillLevel;
		this.skill_name = skill_name;
		
	}
	
	
	public Assessment(String firstName,String lastName, int monthsExp, int skillLevel,  String skill_name,Long devId ) {
		this.monthsExp = monthsExp;
		this.skillLevel = skillLevel;
		this.skill_name = skill_name;
		this.firstName = firstName;
		this.lastName = lastName;
		this.devId = devId;
	}
	
	
	public Assessment(  String skill_name, int skillLevel) {
		this.skill_name = skill_name;
		this.skillLevel = skillLevel;
	
	}
	
//	public Assessment(  String firstName, String lastName, int monthsExp, int skillLevel, Long devId,  String skill_name) {
//		this.firstName = firstName;
//		this.lastName = lastName;
//		this.devId = devId;
//		this.monthsExp = monthsExp;
//		this.skillLevel = skillLevel;
//		this.skill_name = skill_name;
//	
//	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Long getSkillId() {
		return skillId;
	}

	public void setSkillId(Long skillId) {
		this.skillId = skillId;
	}

	public String getSkill_name() {
		return skill_name;
	}

	public void setSkill_name(String skill_name) {
		this.skill_name = skill_name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getDevId() {
		return devId;
	}

	public void setDevId(Long devId) {
		this.devId = devId;
	}

	public int getMonthsExp() {
		return monthsExp;
	}

	public void setMonthsExp(int monthsExp) {
		this.monthsExp = monthsExp;
	}

	public int getSkillLevel() {
		return skillLevel;
	}

	public void setSkillLevel(int skillLevel) {
		this.skillLevel = skillLevel;
	}

}