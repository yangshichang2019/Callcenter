package com.emps.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class SystemDDl {
  private Integer seqid;
  private String keyword;
  private Integer ddlcode;
  private String ddlname;
  
  @Id
  @GeneratedValue(strategy=GenerationType.AUTO)
public Integer getSeqid() {
	return seqid;
}
public void setSeqid(Integer seqid) {
	this.seqid = seqid;
}
public String getKeyword() {
	return keyword;
}
public void setKeyword(String keyword) {
	this.keyword = keyword;
}
public Integer getDdlcode() {
	return ddlcode;
}
public void setDdlcode(Integer ddlcode) {
	this.ddlcode = ddlcode;
}
public String getDdlname() {
	return ddlname;
}
public void setDdlname(String ddlname) {
	this.ddlname = ddlname;
}

}
