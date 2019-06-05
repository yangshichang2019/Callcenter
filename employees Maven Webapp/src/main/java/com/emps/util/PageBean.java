package com.emps.util;

public class PageBean {
  /* 前端页面传入 */
  private int currentPage;   //当前页号
  private int pageSize; //每页显示数量
  
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
public int getAllPageAmount() {
	return allPageAmount;
}
public int getCurrentPage() {
	return currentPage;
}
public int getCurrentPageEndRow() {
	return currentPageEndRow;
}
public int getCurrentPageStartRow() {
	return currentPageStartRow;
}
public int getFirstPage() {
	return firstPage;
}
public int getLastPage() {
	return lastPage;
}
public int getNextPage() {
	return nextPage;
}
public int getPageSize() {
	return pageSize;
}
public int getPrePage() {
	return prePage;
}
public int getTotalCount() {
	return totalCount;
}
public void setAllPageAmount(int allPageAmount) {
	this.allPageAmount = allPageAmount;
}
public void setCurrentPage(int currentPage) {
	this.currentPage = currentPage;
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
public void setPageSize(int pageSize) {
	this.pageSize = pageSize;
}
  public void setPrePage(int prePage) {
	this.prePage = prePage;
}
  public void setTotalCount(int totalCount) {
	this.totalCount = totalCount;
}
  
  
  
  
}
