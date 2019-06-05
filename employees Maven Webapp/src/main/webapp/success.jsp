<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://shiro.apache.org/tags" prefix="shiro" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'success.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  <body>
  <shiro:authenticated>
  欢迎[<shiro:principal/>] 登录，
   <a href="logout">退出</a>
  </shiro:authenticated>
 
    <table width="500px" border="1px" align="center">
     <tr>
       <th>ID</th>
       <th>NAME</th>
       <th>EMAIL</th>
       <th>GENDER</th>
       <th>DEPARTMENT</th>
       <shiro:hasRole name="admin"><th>UPDATE</th>    </shiro:hasRole>
            <shiro:hasPermission name="user:view"><th>DELETE</th>    </shiro:hasPermission>
     </tr>
     <c:forEach items="${requestScope.employees}" var="emps">
     <tr>
       <td>${emps.id}</td>
       <td>${emps.name}</td>
       <td>${emps.email}</td>
       <td>${emps.gender}</td>
       <td>${emps.department}</td>
      <shiro:hasRole name="admin">
       <td>
       <a href="#"> update</a>
       </td> 
       </shiro:hasRole>
       <shiro:hasPermission name="user:view">
       <td>
    
     <a href="#">delete</a>  
    
       </td>  
       </shiro:hasPermission>
     </tr>
     </c:forEach>
    
    </table>
<a href="test">ceshi</a>
  </body>
</html>
