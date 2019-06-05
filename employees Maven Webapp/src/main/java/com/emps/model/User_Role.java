package com.emps.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
@Entity
public class User_Role {
private String id;
private User user;
private Role role;

@ManyToOne
@JoinColumn(name="role_id")
public Role getRole() {
	return role;
}
public void setRole(Role role) {
	this.role = role;
}
@ManyToOne(fetch=FetchType.LAZY)
@JoinColumn(name="user_id")
public User getUser() {
	return user;
}
public void setUser(User user) {
	this.user = user;
}
@Id
@GeneratedValue
public String getId() {
	return id;
}
public void setId(String id) {
	this.id = id;
}


}
