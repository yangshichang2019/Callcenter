package com.emps.pojo;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.emps.model.Department;
import com.emps.util.PageInfo;

public class UserForm2 {
	private Integer user_id;
	private String user_name;
	private String password;
	private String email;
	private Integer status;
	private String created_time;
	private String update_time;
	private Department dept;
	
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
	
	public Integer getUser_id() {
		return user_id;
	}
	public String getUser_name() {
		return user_name;
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
	public String getCreated_time() {
		return created_time;
	}
	public void setCreated_time(String created_time) {
		this.created_time = created_time;
	}
	public String getUpdate_time() {
		return update_time;
	}
	public void setUpdate_time(String update_time) {
		this.update_time = update_time;
	}
	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	
}
