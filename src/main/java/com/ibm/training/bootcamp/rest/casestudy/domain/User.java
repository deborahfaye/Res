package com.ibm.training.bootcamp.rest.casestudy.domain;

//import java.util.Date;
import java.sql.Date;

public class User{
	
	Long devId;
	private String firstName;
	private String middleName;
	private String lastName;
	private Date birthDate;
	private String position;
	
	public User() {
		
	}
	
	public User(String firstName, String middleName, String lastName, Date birthDate, String position ) {
		this(null,firstName, middleName, lastName, birthDate, position);
	}
	
	public User(Long devId, String firstName, String middleName, String lastName, Date birthDate, String position ) {
			this.devId=devId;
			this.firstName=firstName;
			this.middleName=middleName;
			this.lastName=lastName;
			this.birthDate=birthDate;
			this.position=position;
	}

	public Long getid() {
		return devId;
	}

	public void setdevId(Long devId) {
		this.devId = devId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}
	
	
}