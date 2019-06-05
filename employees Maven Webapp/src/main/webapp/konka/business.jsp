<%@ page language="java"  pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<%@ taglib uri="http://shiro.apache.org/tags"  prefix="shiro"%>
<c:import url="/pager/pagerForm.jsp"></c:import>
<script type="text/javascript">

</script>

<div class="pageHeader">
	<form rel="pagerForm" onsubmit="return navTabSearch(this);" action="${pageContext.request.contextPath}/agent/agentList" method="post">
	<div class="searchBar">
		<ul class="searchContent">
			<li>
				<label>客服账号：</label>
				  <input type="text" name="account"  value="${param.account}" />
			<!--	<s:textfield   name="account" value="<s:property value='#agentForm.account' />" />-->
				
			</li>
			<li>
		       <label>客服姓名：</label>
				<input type="text" name="name" id="name" value="${param.name}" />
			</li>
			<li>
		       <label>业务类型：</label>
				<input type="text" name="cor" id="cor" value="${param.cor}" />
			</li>
			<li>
		       <label>呼叫类型：</label>
				<input type="text" name="type" id="type" value="${param.type}"/>
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
			<li><a class="add" href="${pageContext.request.contextPath}/agent/add" target="navTab"><span>添加</span></a></li>
			<li><a class="edit" href="#" target="navTab" warn="请选择一个用户"><span>修改</span></a></li>
			<li class="line">line</li>
			<li><a class="icon" href="${pageContext.request.contextPath}/system/excelExportAction_exportExcel.do" target="dwzExport" targetType="navTab" title="实要导出这些记录吗?"><span>导出EXCEL</span></a></li>
			<!-- <li><a class="icon" href="${pageContext.request.contextPath}/system/excelImportAction_importExcel.do" target=""  minable="false"><span>导入EXCEL</span></a></li> -->
			<li><a class="icon" href="${pageContext.request.contextPath}/system/excelImportAction_importExcel.do" target="navTab" rel="importExcel"><span>批量导入</span></a></li>
			<!--<li><a target="selectedLoad" rel="ids" postType="string" href="demo_page1.html" class="icon"><span>批量Dialog Load逗号分隔</span></a></li>-->
		</ul>
	</div>
	<table class="table" width="1200" layoutH="138">
		<thead>
			<!--  <tr>
				<th width="22"><input type="checkbox" group="ids" class="checkboxCtrl"></th>
				<th width="120" orderField="name" class="asc">客服姓名</th>
				<th orderField="account">客服账号</th>
				<th width="80" orderField="cor">业务类型</th>
				<th width="130" orderField="type">呼叫类型</th>
				<th width="130" >操作</th>
			</tr>
			-->
				<tr>
				<th width="22"><input type="checkbox" group="ids" class="checkboxCtrl"></th>
				<th width="100">客服姓名</th>
				<th width="100">客服账号</th>
				<th width="100">业务类型</th>
				<th width="100">呼叫类型</th>
				<th width="130">操作</th>
			</tr>
		</thead>
		<tbody>
		  <c:forEach items="${requestScope.agentList}" var="agent" varStatus="s">
		    <tr target="agentid" ref="${agent.id}">
		    <td><input name="ids" value="${agent.name}" type="checkbox"></td>
		       <td>${agent.name}</td>
		       <td>${agent.account}</td>
		       <td>${agent.cor}</td>
		       <td>${agent.type}</td>
		       
		       <td>
			
				<a id="delete" title="删除" target="ajaxTodo" href="${pageContext.request.contextPath}/agent/delete/${agent.id}" class="btnDel">删除</a>
		
				
				<a title="编辑" target="navTab" href="${pageContext.request.contextPath}/agent/query/${agent.id}" class="btnEdit">编辑</a>
			
				</td>
		    </tr>
		  </c:forEach>					
		</tbody>
	</table>
	
    <c:import url="/pager/panelBar.jsp"></c:import>
	
	
</div>

