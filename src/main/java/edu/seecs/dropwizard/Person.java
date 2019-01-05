package edu.seecs.dropwizard;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Person {
	@Id
	private int id;
	private String name;
	private String fathername;
	private String organization;
	private String mobile;
	
	public Person() {
		
	}
	public Person(int id, String name, String fname, String org, String mobile) {
		this.id = id;
		this.name = name;
		this.fathername = fname;
		this.organization = org;
		this.mobile = mobile;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getFathername() {
		return fathername;
	}
	public void setFahername(String fathername) {
		this.fathername = fathername;
	}
	public String getOrganization() {
		return organization;
	}
	public void setOrganization(String organization) {
		this.organization = organization;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}	
}