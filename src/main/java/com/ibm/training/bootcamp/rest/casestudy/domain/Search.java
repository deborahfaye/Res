package com.ibm.training.bootcamp.rest.casestudy.domain;

public class Search {

//	Long id;
//	private int skillLevel;
	private String skill_name;
	private int Trained;
	private int Novice;
	private int Proficient;
	private int Advanced;
	private int Expert;
	private int Leader;

	



	public Search() {

	}
	
	
	public Search( String skill_name, int Trained, int Novice, int Proficient, int Advanced, int Expert, int Leader) {
//		this.id = id;
		
		this.skill_name = skill_name;
		this.Trained = Trained;
		this.Novice = Novice;
		this.Proficient = Proficient;
		this.Advanced = Advanced;
		this.Expert = Expert;
		this.Leader = Leader;
	}
	


//	public Long getId() {
//		return id;
//	}
//
//	public void setId(Long id) {
//		this.id = id;
//	}

//	public int getSkillLevel() {
//		return skillLevel;
//	}


	public String getSkill_name() {
		return skill_name;
	}

	public void setSkill_name(String skill_name) {
		this.skill_name = skill_name;
	}

	public int getTrained() {
		return Trained;
	}

	public void setTrained(int trained) {
		Trained = trained;
	}

	public int getNovice() {
		return Novice;
	}

	public void setNovice(int novice) {
		Novice = novice;
	}

	public int getProficient() {
		return Proficient;
	}

	public void setProficient(int proficient) {
		Proficient = proficient;
	}

	public int getAdvanced() {
		return Advanced;
	}

	public void setAdvanced(int advanced) {
		Advanced = advanced;
	}

	public int getExpert() {
		return Expert;
	}

	public void setExpert(int expert) {
		Expert = expert;
	}

	public int getLeader() {
		return Leader;
	}

	public void setLeader(int leader) {
		Leader = leader;
	}

	
	
	
}