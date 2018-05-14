<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<c:set var="cPath" value="${pageContext.servletContext.contextPath}"/>
<nav class="navigation no-drag">
	<p>카테고리</p>
	<c:forEach items="${categoryList}" var="category">
		<a data-no="${category.no}" href="${cPath}/product/list?c=${category.no}">${category.name}</a>
	</c:forEach>
</nav>
<c:if test="${not empty category}">
	<script type="text/javascript" src="${cPath}/assets/js/jquery/jquery-1.9.0.js"></script>
	<script>
      $('.navigation a[data-no="${category.no}"]').addClass("selected");
	</script>
</c:if>