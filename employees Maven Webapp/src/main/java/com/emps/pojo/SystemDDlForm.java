package com.emps.pojo;

import com.emps.util.PageInfo;

public class SystemDDlForm {
	  private Integer seqid;
	  private String keyword;
	  private Integer ddlcode;
	  private String ddlname;
	  private PageInfo page;
	  private AjaxDone ajaxDone = new AjaxDone() ;
	  private String orderField;
	  private String orderDirection;
	  
	  
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
	public PageInfo getPage() {
		return page;
	}
	public void setPage(PageInfo page) {
		this.page = page;
	}
	public AjaxDone getAjaxDone() {
		return ajaxDone;
	}
	public void setAjaxDone(AjaxDone ajaxDone) {
		this.ajaxDone = ajaxDone;
	}
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
