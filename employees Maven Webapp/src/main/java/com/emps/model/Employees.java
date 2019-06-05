package com.emps.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Employees {
  private String id;
  private String name;
  private String email;
  private String gender;
  private String department;
  @Id
  @GeneratedValue
public String getId() {
	return id;
}
public void setId(String id) {
	this.id = id;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
public String getGender() {
	return gender;
}
public void setGender(String gender) {
	this.gender = gender;
}
public String getDepartment() {
	return department;
}
public void setDepartment(String department) {
	this.department = department;
}
  
}
