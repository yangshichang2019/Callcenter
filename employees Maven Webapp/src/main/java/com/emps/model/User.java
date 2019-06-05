package com.emps.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

@Entity
public class User {
private Integer user_id;
private String user_name;
private String password;
private String email;
private SystemDDl ddl;
private Department dept;
private Integer status;
private Date created_time;
private Date update_time;
private Set<User_Role> ur = new HashSet<User_Role>();

public Date getCreated_time() {
	return created_time;
}



@ManyToOne(fetch=FetchType.LAZY)

public SystemDDl getDdl() {
	return ddl;
}
@ManyToOne(fetch=FetchType.LAZY,cascade=CascadeType.ALL)
@JoinColumn(name="deptid")
@NotFound(action=NotFoundAction.IGNORE)
public Department getDept() {
	return dept;
}


public String getEmail() {
	return email;
}
public String getPassword() {
	return password;
}
public Integer getStatus() {
	return status;
}
public Date getUpdate_time() {
	return update_time;
}
@OneToMany(mappedBy="user")
public Set<User_Role> getUr() {
	return ur;
}
@Id
@GeneratedValue
public Integer getUser_id() {
	return user_id;
}
public String getUser_name() {
	return user_name;
}

public void setCreated_time(Date created_time) {
	this.created_time = created_time;
}
public void setDdl(SystemDDl ddl) {
	this.ddl = ddl;
}
public void setDept(Department dept) {
	this.dept = dept;
}
public void setEmail(String email) {
	this.email = email;
}

public void setPassword(String password) {
	this.password = password;
}
public void setStatus(Integer status) {
	this.status = status;
}
public void setUpdate_time(Date update_time) {
	this.update_time = update_time;
}
public void setUr(Set<User_Role> ur) {
	this.ur = ur;
}
public void setUser_id(Integer user_id) {
	this.user_id = user_id;
}
public void setUser_name(String user_name) {
	this.user_name = user_name;
}

}
