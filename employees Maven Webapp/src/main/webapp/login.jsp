<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'logon.jsp' starting page</title>
    
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
  <h3>logon JSP page.</h3>  <br>
  
  <form action="login" method="post">
   <input type="hidden" name="user_id">
    <br/>
    <c:if test="${!empty requestScope.error }">
       <h3>${requestScope.error }</h3>
    </c:if>

    <br>
    USERNAME:<input type="text" name="user_name" value="${user_name}"/>
    <br/>
    PASSWORD:<input type="password" name="password" value="${password}"/>
    <br/>
    <input type="submit" value="登录">
            记住我<input type="checkbox" name="rememberMe">
  </form>
  </body>
</html>
