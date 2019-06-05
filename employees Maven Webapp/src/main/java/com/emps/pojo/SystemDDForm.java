package com.emps.pojo;

import java.util.Date;

import com.emps.util.PageInfo;

public class SystemDDForm {
  private String id;
  private String name;
  private String code;
  private String module;
  private Date create_date;
  private String create_employee;
  private Integer create_dept;
  private String update_dept;
  private String update_employee;
  private Date update_time;
  private String enable_flag;
  private String delete_flag;
  private PageInfo page;
  private String orderField;
  public String getOrderField() {
	return orderField;
}
public void setOrderField(String orderField) {
	this.orderField = orderField;
}
public String getOrderDirection() {
	return orderDirection;
}
public void setOrderDirection(String orderDirection) {
	this.orderDirection = orderDirection;
}
private String orderDirection;
  public PageInfo getPage() {
	return page;
}
public void setPage(PageInfo page) {
	this.page = page;
}
public String getCode() {
	return code;
}
  public Date getCreate_date() {
	return create_date;
}
  public Integer getCreate_dept() {
	return create_dept;
}
  public String getCreate_employee() {
	return create_employee;
}
public String getDelete_flag() {
	return delete_flag;
}
public String getEnable_flag() {
	return enable_flag;
}
public String getId() {
	return id;
}
public String getModule() {
	return module;
}
public String getName() {
	return name;
}
public String getUpdate_dept() {
	return update_dept;
}
public String getUpdate_employee() {
	return update_employee;
}
public Date getUpdate_time() {
	return update_time;
}
public void setCode(String code) {
	this.code = code;
}
public void setCreate_date(Date create_date) {
	this.create_date = create_date;
}
public void setCreate_dept(Integer create_dept) {
	this.create_dept = create_dept;
}
public void setCreate_employee(String create_employee) {
	this.create_employee = create_employee;
}
public void setDelete_flag(String delete_flag) {
	this.delete_flag = delete_flag;
}
public void setEnable_flag(String enable_flag) {
	this.enable_flag = enable_flag;
}
public void setId(String id) {
	this.id = id;
}
public void setModule(String module) {
	this.module = module;
}
public void setName(String name) {
	this.name = name;
}
public void setUpdate_dept(String update_dept) {
	this.update_dept = update_dept;
}
public void setUpdate_employee(String update_employee) {
	this.update_employee = update_employee;
}
public void setUpdate_time(Date update_time) {
	this.update_time = update_time;
}
}
