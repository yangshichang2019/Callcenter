package com.emps.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Permission {
  private String permission_id;
  private String permission_name;
  private Date created_time;
  private Date update_time;
  private Set<Role_Permission> role_permission = new HashSet<Role_Permission>();
  @OneToMany(mappedBy="permission")
public Set<Role_Permission> getRole_permission() {
	return role_permission;
}
public void setRole_permission(Set<Role_Permission> role_permission) {
	this.role_permission = role_permission;
}

@Id
@GeneratedValue
public String getPermission_id() {
	return permission_id;
}
public String getPermission_name() {
	return permission_name;
}
public Date getUpdate_time() {
	return update_time;
}

public Date getCreated_time() {
	return created_time;
}
public void setCreated_time(Date created_time) {
	this.created_time = created_time;
}
public void setPermission_id(String permission_id) {
	this.permission_id = permission_id;
}
public void setPermission_name(String permission_name) {
	this.permission_name = permission_name;
}
  public void setUpdate_time(Date update_time) {
	this.update_time = update_time;
}
}
