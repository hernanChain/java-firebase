package com.firebase.demo.model;

import java.io.Serializable;

public class Student implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6868354095001316408L;
	private String firstName;
	private String lastName;
	private String code;
	private String email;
	private String semester;
	
	
	public Student() {
	}

	public Student(String firstName, String lastName, String code, String email, String semester) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.code = code;
		this.email = email;
		this.semester = semester;
	}
	
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
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSemester() {
		return semester;
	}
	public void setSemester(String semester) {
		this.semester = semester;
	}
	
}
