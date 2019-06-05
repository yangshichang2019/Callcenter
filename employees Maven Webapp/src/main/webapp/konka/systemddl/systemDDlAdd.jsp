<%@ page language="java"  pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:import url="/pager/pagerForm.jsp"></c:import>
<div class="pageContent">
<form action="${pageContext.request.contextPath}/systemddl/saveOrUpdate" method="post"   class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
    <c:set value="${systemDDl }" var="ddl"/>
  
    <c:if test="${! empty ddl }">
    <div class="pageFormContent" layoutH="58">

     <input type="hidden" name="seqid" value="${ddl.seqid }"/>

      <p>
     <label>字典定义：</label>
     <input type="text" name="keyword" value="${ddl.keyword }"/>
   </p> 
      <p>
     <label>属性ID： </label>
        <input  type="text" name="ddlcode" value="${ddl.ddlcode }"> 
     </p> 

    <p> 
     <label>字典属性：</label>
     <input type="text" name="ddlname" value="${ddl.ddlname }"  />
   </p> 

  

  
 </div>
 </c:if>
 <c:if test="${ empty ddl }">
     <div class="pageFormContent" layoutH="58">


      <p>
     <label>字典定义：</label>
     <input type="text" name="keyword" value="${param.keyword }"/>
     </p> 
      <p>
     <label>字典属性ID： </label>
        <input  type="text" name="ddlcode" value="${param.ddlcode }"> 
     </p> 

    <p> 
     <label>字典属性：</label>
     <input type="text" name="ddlname" value="${param.ddlname }"  />
   </p> 
   
 </div>
 </c:if>

   <div class="formBar">
      	<ul>
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">提交</button></div></div></li>
				<li><div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div></li>
			</ul>
   </div>
   

</form>

</div>
