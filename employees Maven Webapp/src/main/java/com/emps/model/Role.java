package com.emps.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Role {
 private String role_id;
 private String role_name;
 private String created_time;
private String update_time;
private Set<Role_Permission> role_permission = new HashSet<Role_Permission>();
 private Set<User_Role> user_role = new HashSet<User_Role>();
 public String getCreated_time() {
	return created_time;
}
 @Id
 @GeneratedValue
public String getRole_id() {
	return role_id;
}
 
 public String getRole_name() {
	return role_name;
}
@OneToMany(mappedBy="role")
 public Set<Role_Permission> getRole_permission() {
	return role_permission;
}
public String getUpdate_time() {
	return update_time;
}
@OneToMany(mappedBy="role")
public Set<User_Role> getUser_role() {
	return user_role;
}
public void setCreated_time(String created_time) {
	this.created_time = created_time;
}
public void setRole_id(String role_id) {
	this.role_id = role_id;
}
public void setRole_name(String role_name) {
	this.role_name = role_name;
}
public void setRole_permission(Set<Role_Permission> role_permission) {
	this.role_permission = role_permission;
}
public void setUpdate_time(String update_time) {
	this.update_time = update_time;
}
public void setUser_role(Set<User_Role> user_role) {
	this.user_role = user_role;
}
 
}
