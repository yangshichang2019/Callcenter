<%@ page language="java"  pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<c:import url="/pager/pagerForm.jsp"></c:import>


<div class="pageContent">

	<form method="post" action="${pageContext.request.contextPath}/agent/update1" class="pageForm required-validate" onsubmit="return validateCallback(this, navTabAjaxDone);">
	       <c:set value="${agentForm}" var="agent"/>                
		<c:if test="${!empty requestScope.agentForm}">
		  
		
		<input type="hidden" name="id" id="id" value="${agent.id}">
		
		<div class="pageFormContent" layoutH="56">
			<p>
				<label>账号：</label>
				<input name="account" value= "${agent.account}">
			</p>
	    
			<p>
				<label>姓名：</label>
					<input name="name" value= "${agent.name}">
			</p>
		
			<p>
				<label>呼叫权限：</label>
				<input name="cor" value= "${agent.cor}">
			</p>
		
			<p>
				<label>呼叫类型：</label>
				<input name="type"  value= "${agent.type}">
			</p>
		</div>
		</c:if>
		<c:if test="${empty requestScope.agentForm}">
		<div class="pageFormContent" layoutH="56">
			<p>
				<label>账号：</label>
				<input name="account" value= "${param.account}">
			</p>
	    
			<p>
				<label>姓名：</label>
					<input name="name" value= "${param.name}">
			</p>
		
			<p>
				<label>呼叫权限：</label>
				<input name="cor" value= "${param.cor}">
			</p>
		
			<p>
				<label>呼叫类型：</label>
				<input name="type"  value= "${param.type}">
			</p>
		</div>
	</c:if>
		<div class="formBar">
			<ul>
				<!--<li><a class="buttonActive" href="javascript:;"><span>保存</span></a></li>-->
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">保存</button></div></div></li>
				<li>
					<div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div>
				</li>
			</ul>
		</div>
	</form>
	
</div>
