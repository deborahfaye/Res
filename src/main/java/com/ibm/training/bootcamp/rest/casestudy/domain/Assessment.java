package com.ibm.training.bootcamp.rest.casestudy.domain;

public class Assessment {

	Long id;
//	Long skillId;
	private Long devId;
	private String skill;
	private int monthsExp;
	private int skillLevel;

	public Assessment() {

	}

	public Assessment(int monthsExp, int skillLevel, Long devId, String skill) {
		this(null, monthsExp, skillLevel, devId, skill);
	}

	public Assessment(Long id, int monthsExp, int skillLevel, Long devId, String skill) {
		this.id = id;
		this.devId = devId;
		this.skill = skill;
		this.monthsExp = monthsExp;
		this.skillLevel = skillLevel;
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

	public String getSkill() {
		return skill;
	}

	public void setSkill(String skill) {
		this.skill = skill;
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