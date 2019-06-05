package com.emps.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.GenericGenerator;
@Entity
public class SystemDD {
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
      private Set<SystemDDItem> sdi = new HashSet<SystemDDItem>();
      
    @OneToMany(mappedBy="systemdd") 
	public Set<SystemDDItem> getSdi() {
		return sdi;
	}
	public void setSdi(Set<SystemDDItem> sdi) {
		this.sdi = sdi;
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
	@Id
	@GeneratedValue(generator="uuid")
	@GenericGenerator(name="uuid",strategy="uuid.hex")
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
