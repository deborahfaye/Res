package com.ibm.training.bootcamp.rest.casestudy.domain;

public class Assessment {

	Long id;
//	Long skillId;
    private Long devId;
//	private Long skillId;
	private int monthsExp;
	private int skillLevel;
	private String skill_name;

	public Assessment() {

	}

	public Assessment(int monthsExp, int skillLevel, Long devId,  String skill_name ) {
		this(null, monthsExp, skillLevel, devId,  skill_name);
	}

	public Assessment(Long id, int monthsExp, int skillLevel, Long devId,  String skill_name) {
		this.id = id;
		this.devId = devId;
//		this.skillId = skillId;
		this.monthsExp = monthsExp;
		this.skillLevel = skillLevel;
		this.skill_name = skill_name;
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

//	public Long getSkillId() {
//		return skillId;
//	}
//
//	public void setSkillId(Long skillId) {
//		this.skillId = skillId;
//	}

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