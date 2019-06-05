package com.emps.pojo;

import java.util.Date;

public class SystemDDItemForm {
	  private String dditem_id ;
	  private String parent_id;
	  private String name;
	  private String value;
	  private Integer sort;
	  private Date create_date;
	  private String create_employee;
	  private Integer create_dept;
	  private String update_employee;
	  private String update_dept;
	  private Date update_time;
	  private String enable_flag;
	  private String delete_flag;
	  private AjaxDone ajaxDone = new AjaxDone();
	  public AjaxDone getAjaxDone() {
		return ajaxDone;
	}
	public void setAjaxDone(AjaxDone ajaxDone) {
		this.ajaxDone = ajaxDone;
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
	  public String getDditem_id() {
		return dditem_id;
	}
	  
	  public String getDelete_flag() {
		return delete_flag;
	}
	public String getEnable_flag() {
		return enable_flag;
	}
	public String getName() {
		return name;
	}
	public String getParent_id() {
		return parent_id;
	}
	public Integer getSort() {
		return sort;
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
	public String getValue() {
		return value;
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
	public void setDditem_id(String dditem_id) {
		this.dditem_id = dditem_id;
	}
	public void setDelete_flag(String delete_flag) {
		this.delete_flag = delete_flag;
	}
	public void setEnable_flag(String enable_flag) {
		this.enable_flag = enable_flag;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setParent_id(String parent_id) {
		this.parent_id = parent_id;
	}
	public void setSort(Integer sort) {
		this.sort = sort;
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
	  public void setValue(String value) {
		this.value = value;
	}
}
