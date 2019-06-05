package com.emps.model;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
@Entity
public class Department {
   private Integer deptId;
   private String deptName;
   private Set<User> users = new HashSet<User>();

   @Id
   @GeneratedValue
public Integer getDeptId() {
	return deptId;
}
public String getDeptName() {
	return deptName;
}
@OneToMany(mappedBy="dept")
public Set<User> getUsers() {
	return users;
}
public void setDeptId(Integer deptId) {
	this.deptId = deptId;
}
public void setDeptName(String deptName) {
	this.deptName = deptName;
}
public void setUsers(Set<User> users) {
	this.users = users;
}
}
