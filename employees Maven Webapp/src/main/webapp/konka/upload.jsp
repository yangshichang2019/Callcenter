<%@ page language="java" pageEncoding="UTF-8"%>
<div class="pageContent">
	<form method="post" action="${pageContext.request.contextPath}/system/excelImportAction_save.do" enctype="multipart/form-data" class="pageForm required-validate" onsubmit="return iframeCallback(this);">
		<div class="pageFormContent" layoutH="56">
			<p>
				<label>导入</label>
			  <input  type="file"  name="excelFile"/>	
			  <s:hidden name="flag" value=""></s:hidden>
			<!--<s:file name="excelFile"></s:file>-->
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
