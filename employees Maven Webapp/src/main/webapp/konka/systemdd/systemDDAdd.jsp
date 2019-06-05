<%@ page language="java"  pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:import url="/pager/pagerForm.jsp"></c:import>
<div class="pageContent">
<form action="${pageContext.request.contextPath}/systemdd/saveOrUpdate" method="post"   class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
    <c:set value="${systemDD }" var="dd"/>
  
    <c:if test="${! empty dd }">
    <div class="pageFormContent" layoutH="58">

     <input type="hidden" name="id" value="${dd.id }"/>

      <p>
     <label>字典名字：</label>
     <input type="text" name="name" value="${dd.name }"/>
   </p> 
      <p>
     <label>模块： </label>
        <input  type="text" name="module" value="${dd.module }"> 
     </p> 

    <p> 
     <label>代码：</label>
     <input type="text" name="code" value="${dd.code }"  />
   </p> 

  

  
 </div>
 </c:if>
 <c:if test="${ empty dd }">
     <div class="pageFormContent" layoutH="58">
     <input type="hidden" name="id" value="${dd.id }"/>

      <p>
     <label>字典名字：</label>
     <input type="text" name="name" value="${dd.name }"/>
   </p> 
      <p>
     <label>模块： </label>
        <input  type="text" name="module" value="${dd.module }"> 
     </p> 

    <p> 
     <label>代码：</label>
     <input type="text" name="code" value="${dd.code }"  />
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
