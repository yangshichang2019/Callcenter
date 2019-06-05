<%@ page language="java"  pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:import url="/pager/pagerForm.jsp"></c:import>

<div class="pageHeader">
	<form hidden="hidden" rel="pagerForm" onsubmit="return navTabSearch(this);" action="${pageContext.request.contextPath}/systemdd/systemDDItemManage/${requestScope.id}" method="post">
	<div class="searchBar">
		<ul class="searchContent">
			<li>
				<label>账号：</label>
				  <input type="text" name="id"  value="${requestScope.id}" />
			
			</li>
		    
		      
		</ul>
		 
	
		<div class="subBar">
			<ul>
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">查询</button></div></div></li>
			</ul>
		</div>
	</div>
	</form>
</div>






<div class="pageContent">
	<div class="panelBar">
		<ul class="toolBar">
		    <c:set  var="id" value="${requestScope.id}"/>
			<li><a class="add" href="${pageContext.request.contextPath}/systemdd/systemDDItemAdd/${id}" target="dialog"><span>增加</span></a></li>
			<li><a class="delete" href="${pageContext.request.contextPath}/systemdd/systemDDItemDelete/{dditem_id}" target="navTab" warn="请选择一行"><span>删除</span></a></li>
			<li class="line">line</li>
			<!--  <li><a class="icon" href="${pageContext.request.contextPath}/system/excelExportAction_exportExcel.do" target="dwzExport" targetType="navTab" title="实要导出这些记录吗?"><span>导出EXCEL</span></a></li>-->
			<!-- <li><a class="icon" href="${pageContext.request.contextPath}/system/excelImportAction_importExcel.do" target=""  minable="false"><span>导入EXCEL</span></a></li> -->
			<!-- <li><a class="icon" href="${pageContext.request.contextPath}/system/excelImportAction_importExcel.do" target="navTab" rel="importExcel"><span>批量导入</span></a></li> -->
			<!--<li><a target="selectedLoad" rel="ids" postType="string" href="demo_page1.html" class="icon"><span>批量Dialog Load逗号分隔</span></a></li>-->
		</ul>
	</div>
	<table class="table" width="1200" layoutH="138">
		<thead>

				<tr>
				<th width="30">序号</th>
				<th width="100">编码项</th>
				<th width="100">编码值</th>
				<th width="100">排序</th>
				<th width="100">状态</th>
				<th width="50">操作</th>
			</tr>
		</thead>
		<tbody>
		
		 
		
		  <c:forEach items="${requestScope.systemDDItemsForm}" var="dditem" varStatus="s">
		     <tr target="dditem_id" rel="${dditem.dditem_id}">
	        
		       <td >${s.index + 1}</td>
		       <td align="center" width="100">${dditem.name}</td>
		       <td align="center" width="100">${dditem.value}</td>
		       <td align="center" width="100">${dditem.sort}</td>
		       <td align="center" width="100">${dditem.enable_flag}</td>	       
		       <td align="center" width="50">

			
	<!-- 	<a id="delete" title="删除" target="ajaxTodo" href="${pageContext.request.contextPath}/agent/delete/${agent.id}" class="btnDel">删除</a>-->		
		
				
			<!-- <a title="编辑" target="dialog" href="${pageContext.request.contextPath}/systemdd/systemDDItemEdit/${dditem.dditem_id}" class="btnEdit">编辑</a> -->	
			
			<a title="编辑" align="center" target="dialog" href="<c:url  value='/systemdd/systemDDItemEdit/${dditem.dditem_id }'/>" class="btnEdit">编辑</a>
			
				</td>
	
		       <td hidden="hidden">${id}</td>
		    </tr>
		  </c:forEach>					
		</tbody>
	</table>
	
    <c:import url="/pager/panelBar.jsp"></c:import>
	
	
</div>
