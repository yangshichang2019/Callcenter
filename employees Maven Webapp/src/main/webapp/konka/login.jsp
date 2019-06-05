<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script type='text/javascript' src='${pageContext.request.contextPath}/js/pub.js'></script>
<script type="text/javascript" src='${pageContext.request.contextPath}/js/validate.js'/></script>

<script type="text/javascript">

</script>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>工号管理系统</title>
<link href="/employees/themes/css/login.css" rel="stylesheet" type="text/css" />
</head>

<body>
	<div id="login">
		<div id="login_header">
			<h1 class="login_logo">
				<img src="/employees/themes/default/images/login_logo.gif" />
			</h1>
			<div class="login_headerContent">
				<div class="navList">
					<ul>
						<li><a href="#">设为首页</a></li>
						<li><a href="http://bbs.dwzjs.com">反馈</a></li>
						<li><a href="doc/dwz-user-guide.pdf" target="_blank">帮助</a></li>
					</ul>
				</div>
				<h2 class="login_title"><img src="/employees/themes/default/images/login_title.gif" /></h2>
			</div>
		</div>
		<div id="login_content">
			<div class="loginForm">
			   <div>
			     <font color="red">
			     <s:fielderror name="error"></s:fielderror>
			     </font>
			     </div>
				<form action="login" method="post" >
				<c:if test="${!empty requestScope.error }">
                  <h3>${requestScope.error }</h3>
                </c:if>
                <br/>
					<p>
						<label>用户名：</label>
					<input type="text" name="user_name"  size="20" class="login_input" />
					
					</p>
					<p>
						<label>密码：</label>
						 <input type="password" name="password"   size="20" class="login_input" />
				
					</p>
				<!-- 	<p>
						<label>验证码：</label>
						<input class="code" type="text" size="5" />
						<span><img src="${pageContext.request.contextPath}/themes/default/images/header_bg.png" alt="" width="75" height="24" /></span>
					</p>
					 -->
					<div class="login_bar">
						<input class="sub" type="submit" value=" " />
					</div>
					
				</form>
			</div>
			<div class="login_banner"><img src="/employees/themes/default/images/login_banner.png" /></div>
			<div class="login_main">
		
			
			</div>
		</div>
		<div id="login_footer">
		
		</div>
	</div>
</body>
</html>