<%@ page language="java"  pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:import url="/pager/pagerForm.jsp"></c:import>
<div class="pageContent">
<form  action="<c:url value='/systemdd/systemDDItemSaveOrUpdate'/>" method="post"   class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
    <c:set value="${requestScope.systemDDItemForm}" var="dditem"/>
  

    <div class="pageFormContent" layoutH="58">
    <c:if test="${empty requestScope.parent_id }">
    <input type="hidden" name="parent_id" value="${dditem.parent_id}">
    </c:if>
    <c:if test="${!empty requestScope.parent_id }">
     <input type="hidden" name="parent_id" value="${requestScope.parent_id}">
    </c:if>
<!--      <input type="hidden" name="parent_id" value="${dditem.parent_id}">--> 
    
     <input type="hidden" name="dditem_id" value="${dditem.dditem_id}"/>

      <p>
     <label>编码项：</label>
     <input type="text" name="name" value="${dditem.name}"/>
   </p> 
      <p>
     <label>编码值： </label>
        <input  type="text" name="value" value="${dditem.value }"> 
     </p> 

    <p> 
     <label>排序：</label>
     <input type="text" name="sort" value="${dditem.sort }"  />
   </p> 
 <p> 
     <label>是否可用：</label>
     可用<input type="radio"  name="enable_flag" value="1"  ${dditem.enable_flag == 1 ? 'checked' : '' }/>
     禁用<input type="radio"  name="enable_flag" value="0"  ${dditem.enable_flag == 0 ? 'checked' : '' }/>
   </p>
  

  
 </div>



   <div class="formBar">
      	<ul>
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">提交</button></div></div></li>
				<li><div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div></li>
			</ul>
   </div>
   

</form>

</div>
