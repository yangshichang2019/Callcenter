<%@ include file="/include.inc.jsp"%>

<form id="pagerForm" method="post" action="#rel#">
	<input type="hidden" name="pageNum" value="${vo.page.pageNum}" />
	<input type="hidden" name="numPerPage" value="${vo.page.numPerPage}" />
    <input type="hidden" name="orderField" value="${vo.orderField}" />
	<input type="hidden" name="orderDirection" value="${vo.orderDirection}" />	
</form>