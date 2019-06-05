<%@ page language="java"  pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>

{
	"statusCode": "${requestScope.ajaxDone.statusCode}",
	"message": "${requestScope.ajaxDone.message}",
	"navTabId":"",
	"rel":"",
	"callbackType":"${requestScope.ajaxDone.callbackType}",
	"forwardUrl":"",
	"confirmMsg":""
}
