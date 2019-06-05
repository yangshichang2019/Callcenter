package com.emps.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.GenericGenerator;
@Entity
public class SystemDDItem implements Serializable {
	  private String dditem_id ;
	  private SystemDD systemdd;
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
	  public Date getCreate_date() {
		return create_date;
	}
	  public Integer getCreate_dept() {
		return create_dept;
	}
	 
	  
	  public String getCreate_employee() {
		return create_employee;
	}
	  @Id
	  @GeneratedValue(generator="uuid")
	  @GenericGenerator(name="uuid",strategy="uuid.hex")
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
	public Integer getSort() {
		return sort;
	}
	@ManyToOne(fetch=FetchType.LAZY)
	  @JoinColumn(name="parent_id")
	  public SystemDD getSystemdd() {
			return systemdd;
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
	public void setSort(Integer sort) {
		this.sort = sort;
	}
	public void setSystemdd(SystemDD systemdd) {
		this.systemdd = systemdd;
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
