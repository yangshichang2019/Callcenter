<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include file="/include.inc.jsp"%>
<c:set var="targetType" value="${empty param.targetType ? 'navTab' : param.targetType}"/>
<div class="panelBar">
	<div class="pages">
		<span>Per Page</span>
		<select name="numPerPage" onchange="navTabPageBreak({numPerPage:this.value})">
			<c:forEach begin="10" end="40" step="10" varStatus="s">
				<option value="${s.index}" ${vo.page.numPerPage eq s.index ? 'selected="selected"' : ''}>${s.index}</option>
			</c:forEach>
		</select>

		<span>Total: ${vo.page.totalCount}</span>
	</div>
	
	<div class="pagination" targetType="${targetType}" totalCount="${vo.page.totalCount}" numPerPage="${vo.page.numPerPage}" pageNumShown="10" currentPage="${vo.page.pageNum}"></div>
</div>