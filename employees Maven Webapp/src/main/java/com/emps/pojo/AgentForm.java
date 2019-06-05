package com.emps.pojo;

import com.emps.util.PageInfo;

public class AgentForm {
private String id;
private String account;
private String name;
private String cor;
private String type;
private String orderField;
private String orderDirection;
private AjaxDone ajaxDone = new AjaxDone() ;
public AjaxDone getAjaxDone() {
	return ajaxDone;
}
public void setAjaxDone(AjaxDone ajaxDone) {
	this.ajaxDone = ajaxDone;
}
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
private PageInfo page;
public PageInfo getPage() {
	return page;
}
public void setPage(PageInfo page) {
	this.page = page;
}


public String getId() {
	return id;
}
public void setId(String id) {
	this.id = id;
}
public String getAccount() {
	return account;
}
public void setAccount(String account) {
	this.account = account;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getCor() {
	return cor;
}
public void setCor(String cor) {
	this.cor = cor;
}
public String getType() {
	return type;
}
public void setType(String type) {
	this.type = type;
}
}
