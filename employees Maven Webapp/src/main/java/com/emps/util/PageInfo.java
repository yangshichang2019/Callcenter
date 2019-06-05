package com.emps.util;

import javax.servlet.http.HttpServletRequest;

public class PageInfo {
	  /* 前端页面传入 */
	  private int pageNum;   //当前页号
	  private int numPerPage; //每页显示数量
	  
	  /* 数据库查询 */
	  private int totalCount; //总记录数
	  /* 计算 */
	  private int allPageAmount; //页面数
	  private int currentPageStartRow; //当前页开始行
	  private int currentPageEndRow; //当前页结束行
	  private int firstPage = 1;  //第一页页号
	  private int lastPage;   //最优一页页号
	  private int prePage;  //前一页
	  private int nextPage; //后一页
	  private HttpServletRequest request;
	  /*构造方法*/
	  public PageInfo(HttpServletRequest  request){
		  System.out.println("numPerPage:" + request.getParameter("numPerPage"));
		  pageNum = request.getParameter("pageNum") !=null && !request.getParameter("pageNum").equals("") ? new Integer(request.getParameter("pageNum")).intValue() : 1;
		  numPerPage = request.getParameter("numPerPage") !=null && !request.getParameter("numPerPage").equals("") ? new Integer(request.getParameter("numPerPage")).intValue() : 10;
	  }
	  public void calculatePage(){
		  /*获取总页数*/
		  if(totalCount%numPerPage==0){
			 this.allPageAmount = totalCount/numPerPage;
		  }else{
			  this.allPageAmount = totalCount/numPerPage +1;
		  }
		  /* 获取当前页开始/结束记录*/
		  if(pageNum*numPerPage<totalCount){
		  this.currentPageStartRow = (this.pageNum-1)*numPerPage ;
		  this.currentPageEndRow =this.pageNum*numPerPage;
		  }else {
			  this.currentPageStartRow=(allPageAmount-1)*numPerPage;
			  this.currentPageEndRow=totalCount;
		  }
		  if(pageNum<0){
			  this.currentPageStartRow=0;
		  }
		  /*最后一页*/
		  this.lastPage=this.allPageAmount;
		  /*前一页*/
		  if(this.pageNum>1){
			  this.prePage=this.pageNum-1;
		  }else{
			  this.prePage=this.pageNum;
		  }
		  /*后一页*/
		  if(this.pageNum<this.allPageAmount){
			  this.nextPage=this.pageNum+1;
		  }else{
			  this.nextPage=this.allPageAmount;
		  }
	  }
	  public int getAllPageAmount() {
		return allPageAmount;
	}
	/*获取当前页号*/
	  public int getPageNum(){
		  return pageNum;
	  }
	public int getCurrentPageEndRow() {
		return currentPageEndRow;
	}
	public int getCurrentPageStartRow() {
		return currentPageStartRow;
	}
	/* 第一页*/
	  public int getFirstPage(){
		  return this.firstPage=1;
	  }
	public int getLastPage() {
		return lastPage;
	}
	public int getNextPage() {
		return nextPage;
	}

	public int getNumPerPage() {
		return numPerPage;
	}
	public int getPrePage() {
		return prePage;
	}
	/*获取总记录数*/
	  public int getTotalCount(){  
		  return this.totalCount;
	  }
	public void setAllPageAmount(int allPageAmount) {
		this.allPageAmount = allPageAmount;
	}
	public void setCurrentPage(int pageNum) {
		this.pageNum = pageNum;
	}
	public void setCurrentPageEndRow(int currentPageEndRow) {
		this.currentPageEndRow = currentPageEndRow;
	}
	public void setCurrentPageStartRow(int currentPageStartRow) {
		this.currentPageStartRow = currentPageStartRow;
	}
	public void setFirstPage(int firstPage) {
		this.firstPage = firstPage;
	}
	public void setLastPage(int lastPage) {
		this.lastPage = lastPage;
	}
	public void setNextPage(int nextPage) {
		this.nextPage = nextPage;
	}
	public void setnumPerPage(int numPerPage) {
		this.numPerPage = numPerPage;
	}
	  public void setPrePage(int prePage) {
		this.prePage = prePage;
	}
	
	  public void setTotalCount(int i){
		  this.totalCount = i;
	  }
	 
	  
}
