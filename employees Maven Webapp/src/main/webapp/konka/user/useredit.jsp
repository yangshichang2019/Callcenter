<%@ page language="java"  pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:import url="/pager/pagerForm.jsp"></c:import>
<div class="pageContent">
<form action="${pageContext.request.contextPath}/userManage/saveOrUpdate" method="post"   class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
  <c:set value="${userForm }" var="user"/>
  <c:if test="${! empty requestScope.userForm }">
    
  
   <input type="hidden" name="user_id" id="user_id" value="${user.user_id }"/> 
    <div class="pageFormContent" layoutH="58">
   <p>
     <label>用户名：</label>
     <input type="text" name="user_name" value="${user.user_name }"/>
   </p> 
    <p>
     <label>邮件：</label>
     <input type="text" name="email" value="${user.email }"/>
   </p> 

    <p> 
     <label>部门：</label>
     <input type="text" name="dept.deptId" value="${user.dept.deptId }"  />
   </p> 
    <select ></select>
     <p>
     <label>密码： </label>
        <input type="password" name="password" value="${user.password }"> 
     </p> 
         
     <p>
  
       <label>创建时间：</label>
     <input type="text" name="created_time"  value="${user.created_time }"/>
     </p>
      
       <p>
      <label>修改时间：</label>
     <input type="text" name="update_time"  value="${user.update_time }"/>
     </p>
     
     <p>
     <label>是否可用：</label>     
     <input type="radio" name="status" value="1" ${user.status == 1 ? 'checked' : '' }/>可用
      <input type="radio" name="status" value="0" ${user.status == 0 ? 'checked' : '' }/> 不可用 
   </p> 
   </div> 
   <div class="formBar">
      	<ul>
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">提交</button></div></div></li>
				<li><div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div></li>
			</ul>
   </div>
   
</c:if>
</form>

</div>
